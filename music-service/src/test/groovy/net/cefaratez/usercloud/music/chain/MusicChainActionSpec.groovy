package net.cefaratez.usercloud.music.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.music.domain.Album
import net.cefaratez.usercloud.music.domain.Artist
import net.cefaratez.usercloud.music.service.ArtistService
import ratpack.test.embed.EmbeddedApp
import spock.lang.Specification

class MusicChainActionSpec extends Specification {

    ArtistService artistService
    MusicChainAction musicChainAction
    EmbeddedApp embeddedApp

    def setup() {
        artistService = Mock ArtistService
        musicChainAction = new MusicChainAction(artistService)
        embeddedApp = EmbeddedApp.fromHandlers(musicChainAction)
    }

    def "GET artist by name returns artist"() {
        given:
        artistService.getArtistByName(_ as String) >> {
            new Artist(id: '123ABC',
                       name: 'Queens Ryche',
                       albums: [new Album(name: 'Operation Mind Crime', year: 1984, songs: null)])
//            new Artist().with {
//                id = '123ABC'
//                name = 'Queens Ryche'
//                albums = [
//                        new Album().with {
//                            name = 'Operation Mind Crime'
//                            year = 1984
//                            songs = null
//                        }
//                ]
//            }
        }

        when:
        String response = embeddedApp.httpClient.params { builder ->
            builder.put('name', 'Queens Ryche').build()
        }.getText('artists')

        Artist artist = new JsonSlurper().parseText(response) as Artist

        then:
        artist
        artist.id == '123ABC'
        artist.name == 'Queens Ryche'
    }
}
