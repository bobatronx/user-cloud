package net.cefaratez.usercloud.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'artists')
class Artist {

    @Id
    String id
    String name
    List<Album> albums
}
