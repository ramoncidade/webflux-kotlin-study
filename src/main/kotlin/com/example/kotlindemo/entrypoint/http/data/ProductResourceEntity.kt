package com.example.kotlindemo.entrypoint.http.data

import com.example.kotlindemo.usecase.data.ProductUsecaseEntity
import java.util.*

data class ProductResourceEntity(val id: UUID?,val name: String, val price:Double){
    constructor(usecaseEntity: ProductUsecaseEntity):this(usecaseEntity.id,usecaseEntity.name,usecaseEntity.price)
}
