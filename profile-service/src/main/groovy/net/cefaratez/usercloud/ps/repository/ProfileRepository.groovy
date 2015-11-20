package net.cefaratez.usercloud.ps.repository

import net.cefaratez.usercloud.ps.domain.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository extends MongoRepository<Profile, String> {
}
