package net.cefaratez.usercloud.config

import groovy.json.JsonSlurper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ratpack.hystrix.HystrixModule

@Configuration
class ModuleConfig {

    @Bean
    HystrixModule hystrixModule() {
        new HystrixModule().sse()
    }

    @Bean
    JsonSlurper jsonSlurper() {
        new JsonSlurper()
    }
}
