package net.cefaratez.usercloud.music.config

import com.mongodb.Mongo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.SimpleMongoDbFactory

@Configuration
class MongoConfig {

    @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo(), 'usercloud')
    }

}
