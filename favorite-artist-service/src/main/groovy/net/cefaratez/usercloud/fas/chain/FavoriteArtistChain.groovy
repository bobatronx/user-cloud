package net.cefaratez.usercloud.music.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.music.service.MapProfileUtil
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import ratpack.groovy.handling.GroovyChainAction
import ratpack.http.client.HttpClient
import ratpack.http.client.ReceivedResponse
import rx.Observable
import static ratpack.jackson.Jackson.json

@Component
class FavoriteArtistChain extends GroovyChainAction {

    @Override
    void execute() throws Exception {
        prefix('favorites') {
            get(':profileId') { HttpClient httpClient ->
                URI profileURI = UriComponentsBuilder.fromHttpUrl('http://localhost:5050/profiles/{profileId}')
                    .buildAndExpand(getPathTokens().get('profileId'))
                    .toUri()

                Observable<ReceivedResponse> oResponse = httpClient.get(profileURI).observe()

                oResponse.map { response ->
                    MapProfileUtil.mapProfileResponseToProfile(new JsonSlurper().parse(response.body.bytes))
                }.flatMap { profileWithArtist ->
                    URI artistURI = UriComponentsBuilder.fromHttpUrl('http://localhost:5051/artists?{favoriteArtist}')
                            .buildAndExpand(profileWithArtist.favoriteArtist)
                            .toUri()

                    httpClient.get(artistURI).observe().map { artistResponse ->
                        MapProfileUtil.mapArtistResponseToProfile(profileWithArtist, new JsonSlurper().parse(artistResponse.body.bytes))
                    }
                }.subscribe { fullProfile ->
                    render json(fullProfile)
                }
            }
        }
    }
}
