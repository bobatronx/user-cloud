package net.cefaratez.usercloud.ps

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import ratpack.spring.config.EnableRatpack

@SpringBootApplication
@EnableRatpack
class ProfileServiceApplication {

    static void main(String... args) throws Exception {
        SpringApplication.run(ProfileServiceApplication, args)
    }
}
