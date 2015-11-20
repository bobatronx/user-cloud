package net.cefaratez.usercloud.ms.repository

import net.cefaratez.usercloud.ms.domain.Artist
import org.springframework.data.mongodb.repository.MongoRepository

interface ArtistRepository extends MongoRepository<Artist, String> {

    Artist findArtistByName(String name)
}
