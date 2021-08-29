package com.example.kotlindemo.gateway.impl.data

import com.example.kotlindemo.gateway.data.ProductIntegrationEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
class ProductDBEntity(@Id var id: UUID?= UUID.randomUUID(), val name: String="", val price:Double=0.0){
    constructor(input: ProductIntegrationEntity):this(id= input.id, name=input.name, price= input.price)
}