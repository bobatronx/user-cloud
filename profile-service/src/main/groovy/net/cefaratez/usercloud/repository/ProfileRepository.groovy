package net.cefaratez.usercloud.repository

import net.cefaratez.usercloud.domain.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository extends MongoRepository<Profile, String> {
}
