package net.cefaratez.usercloud.fas.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.fas.service.MapProfileUtil
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import ratpack.groovy.handling.GroovyChainAction
import ratpack.http.HttpUrlBuilder
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

                URI profileURI = HttpUrlBuilder.http()
                        .host('localhost')
                        .port(5050)
                        .path("profiles/${pathTokens.get('profileId')}")
                        .build()

                Observable<ReceivedResponse> oResponse = httpClient.get(profileURI).observe()

                oResponse.map { response ->
                    MapProfileUtil.mapProfileResponseToProfile(new JsonSlurper().parse(response.body.bytes))
                }.flatMap { profileWithArtist ->

                    URI artistURI = HttpUrlBuilder.http()
                        .host('localhost')
                        .port(5051)
                        .path('artists')
                        .params('name', "$profileWithArtist.favoriteArtist")
                        .build()

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
