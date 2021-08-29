package com.example.kotlindemo.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.example.kotlindemo.gateway.impl.repository"])
@Configuration
class JPAConfiguration {
}