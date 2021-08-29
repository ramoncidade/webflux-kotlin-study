package com.example.kotlindemo.gateway

import com.example.kotlindemo.gateway.data.ProductIntegrationEntity
import com.example.kotlindemo.gateway.impl.data.ProductDBEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface ProductGateway {
    fun getAll(): Flux<ProductIntegrationEntity>
    fun getById(id: UUID): Mono<ProductIntegrationEntity>
    fun save(product: ProductIntegrationEntity): Mono<ProductIntegrationEntity>
    fun delete(id:UUID): Mono<Void>
}