package com.example.kotlindemo.gateway.data

import com.example.kotlindemo.usecase.data.ProductUsecaseEntity
import java.util.*

data class ProductIntegrationEntity(var id: UUID?,val name: String, val price:Double){
    constructor(input: ProductUsecaseEntity):this(id= input.id, name=input.name, price= input.price)
    {
        if (this.id != null){
            this.id = UUID.randomUUID()
        }
    }

}
