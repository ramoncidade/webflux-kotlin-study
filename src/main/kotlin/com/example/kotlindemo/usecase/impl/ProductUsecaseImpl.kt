package com.example.kotlindemo.usecase.impl

import com.example.kotlindemo.gateway.ProductGateway
import com.example.kotlindemo.gateway.data.IntegrationException
import com.example.kotlindemo.gateway.data.ProductIntegrationEntity
import com.example.kotlindemo.gateway.impl.data.ProductDBEntity
import com.example.kotlindemo.usecase.ProductUsecase
import com.example.kotlindemo.usecase.data.ProductUsecaseEntity
import com.example.kotlindemo.usecase.data.ResourceException
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Component
class ProductUsecaseImpl(val productGateway: ProductGateway):ProductUsecase{
    override fun getAll(): Flux<ProductUsecaseEntity> {
        return productGateway.getAll().map { ProductUsecaseEntity(it) }
    }

    override fun getById(id: UUID): Mono<ProductUsecaseEntity> {
        return productGateway
                    .getById(id).map { ProductUsecaseEntity(it) }
                    .switchIfEmpty { Mono.empty() }
                    .onErrorResume { error -> ResourceException(error.localizedMessage).toMono() }
    }

    override fun save(product: ProductUsecaseEntity): Mono<ProductUsecaseEntity> {
        var integrationEntity = ProductIntegrationEntity(product)
        return productGateway.save(integrationEntity)
            .map { ProductUsecaseEntity(it) }
            .switchIfEmpty { Mono.empty() }
            .onErrorResume { error -> ResourceException(error.localizedMessage).toMono() }
    }

    override fun delete(id: UUID): Mono<Void> {
        return productGateway.delete(id)
            .onErrorResume { error -> ResourceException(error.localizedMessage).toMono() }
    }

}