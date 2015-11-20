package net.cefaratez.usercloud.ps.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.ps.chain.ProfileChainAction
import net.cefaratez.usercloud.ps.domain.Profile
import net.cefaratez.usercloud.ps.service.ProfileService
import ratpack.test.embed.EmbeddedApp
import rx.Observable
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson

class ProfileChainActionSpec extends Specification {

    EmbeddedApp embeddedApp

    ProfileChainAction profileChainAction
    ProfileService profileService
    JsonSlurper jsonSlurper

    def setup() {
        jsonSlurper = new JsonSlurper()

        profileService = Mock ProfileService
        profileChainAction = new ProfileChainAction(profileService)
        embeddedApp = EmbeddedApp.fromHandlers(profileChainAction)
    }

    def 'GET /profiles returns list of profiles'() {
        given:
        profileService.getProfiles() >> Observable.just([new Profile(firstName: 'James', lastName: 'Cefaratti'),
            new Profile(firstName: 'Josh', lastName: 'Cefaratti')])


        when:
        String response = embeddedApp.httpClient.getText('profiles')

        and:
        List<Profile> profiles = jsonSlurper.parseText(response) as List<Profile>

        then:
        profiles
        profiles.size() == 2
        profiles[0].firstName == 'James'
        profiles[0].lastName == 'Cefaratti'
        profiles[1].firstName == 'Josh'
        profiles[1].lastName == 'Cefaratti'
    }

    def 'POST /profiles returns updated profile'() {
        given:
        profileService.saveProfile(_ as Profile) >> Observable.just(new Profile(id: '1', firstName: 'James', lastName: 'Cefaratti'))

        when:
        String response = embeddedApp.httpClient.requestSpec { spec ->
            spec.body { b ->
                b.text(toJson([id: '1', firstName: 'James', lastName: 'Cefaratti']))
            }
        }.postText('profiles')

        and:
        Profile profile = jsonSlurper.parseText(response) as Profile

        then:
        profile
        profile.id == '1'
        profile.firstName == 'James'
        profile.lastName == 'Cefaratti'
    }

    def 'GET /profiles/{id} returns single profile'() {
        given:
        profileService.getProfileById(_ as String) >> Observable.just(new Profile(id: '1', firstName: 'James', lastName: 'Cefaratti'))

        when:
        String response = embeddedApp.httpClient.getText('profiles/1')

        and:
        Profile profile = jsonSlurper.parseText(response) as Profile

        then:
        profile
        profile.id == '1'
        profile.firstName == 'James'
        profile.lastName == 'Cefaratti'
    }
}
