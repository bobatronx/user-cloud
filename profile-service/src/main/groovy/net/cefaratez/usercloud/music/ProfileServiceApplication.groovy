package net.cefaratez.usercloud.music

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import ratpack.spring.config.EnableRatpack

@SpringBootApplication
@EnableRatpack
@EnableHystrixDashboard
class ProfileServiceApplication {

    static void main(String... args) throws Exception {
        SpringApplication.run(ProfileServiceApplication, args)
    }
}
