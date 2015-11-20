package net.cefaratez.usercloud.ps.chain

import groovy.json.JsonSlurper
import net.cefaratez.usercloud.ps.command.GetAllProfilesCommand
import net.cefaratez.usercloud.ps.command.GetProfileByIdCommand
import net.cefaratez.usercloud.ps.command.SaveProfileCommand
import net.cefaratez.usercloud.ps.domain.Profile
import net.cefaratez.usercloud.ps.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ratpack.groovy.handling.GroovyChainAction
import rx.Observable

import static ratpack.jackson.Jackson.json

@Component
class ProfileChainAction extends GroovyChainAction {

    final ProfileService profileService

    @Autowired
    ProfileChainAction(final ProfileService profileService) {
        this.profileService = profileService
    }

    @Override
    void execute() throws Exception {
        path('profiles', {
            byMethod {
                get {
                    new GetAllProfilesCommand(profileService).observe().subscribe { profiles ->
                        render json(profiles)
                    }
                }
                post {

                    request.body.observe().map { body ->
                        new JsonSlurper().parseText(body.text) as Map
                    }.flatMap { objectMap ->
                        Profile profile = new Profile(objectMap)
                        new SaveProfileCommand(profileService, profile).observe()
                    }.subscribe { user ->
                        render json(user)
                    }
                }
            }
        })

        prefix('profiles') {
            get(':profileId') {

                Observable<Profile> oUser = new GetProfileByIdCommand(profileService, getPathTokens().get('profileId')).observe()

                oUser.subscribe { user ->
                    render json(user)
                }
            }
        }
    }
}
