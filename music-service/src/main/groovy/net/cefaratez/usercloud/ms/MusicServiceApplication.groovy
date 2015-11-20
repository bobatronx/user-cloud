package net.cefaratez.usercloud.ms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import ratpack.spring.config.EnableRatpack

@SpringBootApplication
@EnableRatpack
//@EnableHystrixDashboard
class MusicServiceApplication {

    static void main(String... args) throws Exception {
        SpringApplication.run(MusicServiceApplication, args)
    }
}
