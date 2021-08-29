package com.example.kotlindemo.gateway.impl.repository

import com.example.kotlindemo.gateway.impl.data.ProductDBEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ProductRepository : ReactiveCrudRepository<ProductDBEntity, UUID> {
}