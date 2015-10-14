package net.cefaratez.usercloud.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ratpack.hystrix.HystrixModule

@Configuration
class ModuleConfig {

    @Bean
    HystrixModule hystrixModule() {
        new HystrixModule()
    }
}
