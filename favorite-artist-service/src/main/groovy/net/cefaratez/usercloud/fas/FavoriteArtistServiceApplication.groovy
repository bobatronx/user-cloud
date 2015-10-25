package net.cefaratez.usercloud.music

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import ratpack.spring.config.EnableRatpack

@SpringBootApplication
@EnableRatpack
class FavoriteArtistServiceApplication {

    static void main(String... args) throws Exception {
        SpringApplication.run(FavoriteArtistServiceApplication, args)
    }
}
