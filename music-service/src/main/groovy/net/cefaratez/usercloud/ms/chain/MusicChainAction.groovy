package net.cefaratez.usercloud.ms.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.ms.command.GetArtistByNameCommand
import net.cefaratez.usercloud.ms.command.SaveArtistCommand
import net.cefaratez.usercloud.ms.command.GetArtistByIdCommand
import net.cefaratez.usercloud.ms.command.GetArtistsCommand
import net.cefaratez.usercloud.ms.domain.Artist
import net.cefaratez.usercloud.ms.service.ArtistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.jackson.Jackson.json

@Component
class MusicChainAction extends GroovyChainAction {

    final ArtistService artistService

    @Autowired
    MusicChainAction(ArtistService artistService) {
        this.artistService = artistService
    }

    @Override
    void execute() throws Exception {
        path('artists') {
            byMethod {
                get {

                    String artistName = request.queryParams.get('name')

                    if(artistName) {
                        new GetArtistByNameCommand(artistService, artistName).observe().subscribe { artist ->
                            render json(artist)
                        }
                    } else {
                        new GetArtistsCommand(artistService).observe().subscribe { artists ->
                            render json(artists)
                        }
                    }
                }

                post {
                    request.body.observe().map { body ->
                        new JsonSlurper().parseText(body.text) as Map
                    }.flatMap { objectMap ->
                        Artist artist = new Artist(objectMap)
                        new SaveArtistCommand(artistService, artist).observe()
                    }.subscribe { user ->
                        render json(user)
                    }
                }
            }
        }

        prefix('artists') {
            get(':artistId') {
                new GetArtistByIdCommand(artistService, getPathTokens().get('artistId'))
                        .observe().subscribe { artist ->
                    render json(artist)
                }
            }


        }
    }
}
