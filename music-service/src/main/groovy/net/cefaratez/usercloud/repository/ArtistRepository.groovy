package net.cefaratez.usercloud.repository

import net.cefaratez.usercloud.domain.Artist
import org.springframework.data.mongodb.repository.MongoRepository

interface ArtistRepository extends MongoRepository<Artist, String> {

    Artist findArtistByName(String name)
}
