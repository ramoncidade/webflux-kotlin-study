package com.example.kotlindemo.usecase

import com.example.kotlindemo.usecase.data.ProductUsecaseEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface ProductUsecase {
    fun getAll(): Flux<ProductUsecaseEntity>
    fun getById(id: UUID): Mono<ProductUsecaseEntity>
    fun save(product: ProductUsecaseEntity): Mono<ProductUsecaseEntity>
    fun delete(id: UUID): Mono<Void>
}
