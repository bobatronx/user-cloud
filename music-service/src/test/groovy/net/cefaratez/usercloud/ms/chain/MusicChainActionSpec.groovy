package net.cefaratez.usercloud.ms.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.ms.domain.Album
import net.cefaratez.usercloud.ms.domain.Artist
import net.cefaratez.usercloud.ms.service.ArtistService
import ratpack.test.embed.EmbeddedApp
import rx.Observable
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson

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
            Observable.just(new Artist(id: '123ABC',
                       name: 'Queens Ryche',
                       albums: [new Album(name: 'Operation Mind Crime', year: 1984, songs: null)]))
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

    def "GET /artists returns a list of artists"() {
        given:
        artistService.getArtists() >> {
            Observable.just([new Artist(id: '123ABC',
                name: 'Queens Ryche',
                albums: [new Album(name: 'Operation Mind Crime', year: 1984, songs: null)])])
        }

        when:
        String response = embeddedApp.httpClient.getText('artists')
        List<Artist> artists = new JsonSlurper().parseText(response) as List<Artist>

        then:
        artists
        artists.size() == 1
        artists.head().name == 'Queens Ryche'
    }

    def "GET /artists[id} returns the artist with the given id"() {

        given:
        artistService.getArtistById(_ as String) >> {
            Observable.just(new Artist(id: '123ABC',
                    name: 'Queens Ryche',
                    albums: [new Album(name: 'Operation Mind Crime', year: 1984, songs: null)]))
        }

        when:
        String response = embeddedApp.httpClient.getText('artists/123ABC')
        Artist artist = new JsonSlurper().parseText(response) as Artist

        then:
        artist
        artist.name == 'Queens Ryche'
        artist.albums
        artist.albums.size() == 1
        Album album = artist.albums.head()
        album
        album.name == 'Operation Mind Crime'
        album.year == 1984
        !album.songs
    }

    def "POST /artists returns newly created artist"() {
        given:
        artistService.saveArtist(_ as Artist) >> {artist -> Observable.just(artist)}

        when:
        String response = embeddedApp.httpClient.requestSpec { spec ->
            spec.body { b ->
                b.text(toJson([id: '123ABC', name: 'Operation Mind Crime']))
            }
        }.postText('artists')

        Artist artist = new JsonSlurper().parseText(response) as Artist

        then:
        artist
        artist.id == '123ABC'
        artist.name == 'Operation Mind Crime'
    }
}
