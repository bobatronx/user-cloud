package net.cefaratez.usercloud.music.repository

import net.cefaratez.usercloud.music.domain.Artist
import org.springframework.data.mongodb.repository.MongoRepository

interface ArtistRepository extends MongoRepository<Artist, String> {

    Artist findArtistByName(String name)
}
