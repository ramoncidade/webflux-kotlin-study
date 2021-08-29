package com.example.kotlindemo.gateway.impl

import com.example.kotlindemo.gateway.ProductGateway
import com.example.kotlindemo.gateway.data.IntegrationException
import com.example.kotlindemo.gateway.data.ProductIntegrationEntity
import com.example.kotlindemo.gateway.impl.data.ProductDBEntity
import com.example.kotlindemo.gateway.impl.repository.ProductRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*
@Component
class ProductDBGateway(val repository: ProductRepository):ProductGateway {
    override fun getAll(): Flux<ProductIntegrationEntity> {
        return repository
            .findAll()
            .map { ProductIntegrationEntity(it.id,it.name,it.price) }
            .onErrorResume { error -> IntegrationException(error.localizedMessage).toMono() }
    }

    override fun getById(id: UUID): Mono<ProductIntegrationEntity> {
        return repository.findById(id).map { ProductIntegrationEntity(it.id,it.name,it.price) }.onErrorResume { error -> IntegrationException(error.localizedMessage).toMono() }
    }

    override fun save(product: ProductIntegrationEntity): Mono<ProductIntegrationEntity> {
        return repository.save(ProductDBEntity(product)).map { ProductIntegrationEntity(it.id,it.name,it.price) }.onErrorResume { error -> IntegrationException(error.localizedMessage).toMono() }
    }

    override fun delete(id: UUID): Mono<Void> {
        return repository.deleteById(id).onErrorResume { error -> IntegrationException(error.localizedMessage).toMono() }
    }
}