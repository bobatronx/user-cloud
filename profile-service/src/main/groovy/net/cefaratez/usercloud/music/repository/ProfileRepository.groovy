package net.cefaratez.usercloud.music.repository

import net.cefaratez.usercloud.music.domain.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository extends MongoRepository<Profile, String> {
}
