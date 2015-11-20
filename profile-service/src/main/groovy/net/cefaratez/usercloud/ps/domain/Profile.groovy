package net.cefaratez.usercloud.ps.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'profiles')
class Profile {

    @Id
    String id
    String firstName
    String lastName
    String favoriteArtist
    ContactInformation contactInformation
    Address address
}
