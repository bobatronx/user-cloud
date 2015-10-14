package net.cefaratez.usercloud

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import ratpack.spring.config.EnableRatpack

@SpringBootApplication
@EnableRatpack
@EnableHystrixDashboard
class MusicServiceApplication {

    static void main(String... args) throws Exception {
        SpringApplication.run(MusicServiceApplication, args)
    }
}
