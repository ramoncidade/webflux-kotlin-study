package com.example.kotlindemo.entrypoint.http

import com.example.kotlindemo.entrypoint.http.data.ProductResourceEntity
import com.example.kotlindemo.usecase.ProductUsecase
import com.example.kotlindemo.usecase.data.ProductUsecaseEntity
import com.example.kotlindemo.usecase.data.ResourceException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping(path=["/product"])
class ProductController(val productUsecase: ProductUsecase) {

    @GetMapping
    fun getAll(): Flux<ProductResourceEntity>{
        return productUsecase.getAll()
            .map { ProductResourceEntity(id=it.id,name=it.name,price = it.price) }

    }

    @GetMapping(path = ["/{id}"])
    fun getById(@PathVariable id: String): Mono<ServerResponse> {
        return productUsecase.getById(UUID.fromString(id))
            .map { ProductResourceEntity(it) }
            .flatMap { ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it) }
            .switchIfEmpty(ServerResponse.notFound().build())
            .onErrorResume { error ->
                var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                when(error){
                    is ResourceException -> status = HttpStatus.BAD_GATEWAY;
                    is Exception -> status = HttpStatus.INTERNAL_SERVER_ERROR
                }
                ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON).bodyValue(error)
            }

    }

    @PostMapping
    fun save(@RequestBody body:ProductResourceEntity):Mono<ServerResponse>{
       return productUsecase
           .save(ProductUsecaseEntity(id = body.id,name=body.name,price = body.price))
           .flatMap { ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it) }
           .switchIfEmpty(ServerResponse.notFound().build())
           .onErrorResume { error ->
               var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
               when(error){
                   is ResourceException -> status = HttpStatus.BAD_GATEWAY;
                   is Exception -> status = HttpStatus.INTERNAL_SERVER_ERROR
               }
               ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON).bodyValue(error)
           }

    }

    @PutMapping(path= ["/{id}"])
    fun update(id: String,body: ProductResourceEntity): Mono<ServerResponse> {
       return productUsecase.save(ProductUsecaseEntity(id =id,name= body.name,price=body.price)).flatMap { ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it) }
            .switchIfEmpty(ServerResponse.notFound().build())
            .onErrorResume { error ->
                var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                when(error){
                    is ResourceException -> status = HttpStatus.BAD_GATEWAY;
                    is Exception -> status = HttpStatus.INTERNAL_SERVER_ERROR
                }
                ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON).bodyValue(error)
            }
    }
}