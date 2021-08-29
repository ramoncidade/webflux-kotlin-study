package com.example.kotlindemo.usecase.data

import com.example.kotlindemo.gateway.data.ProductIntegrationEntity
import java.util.*

data class ProductUsecaseEntity(val id: UUID?,val name: String, val price:Double){
    constructor(integrationEntity: ProductIntegrationEntity):this(id= integrationEntity.id, name=integrationEntity.name, price= integrationEntity.price)
    constructor(id: String?, name: String, price:Double):this(id= UUID.fromString(id), name=name, price= price)
}

