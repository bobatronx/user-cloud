package net.cefaratez.usercloud.music.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.music.domain.Profile
import net.cefaratez.usercloud.music.service.ProfileService
import rx.Observable

/**
 * Created by James Cefaratti
 * 10/11/15.
 */
class GetAllProfilesCommand extends HystrixObservableCommand<List<Profile>> {

    final ProfileService profileService

    GetAllProfilesCommand(final ProfileService profileService) {

        super(HystrixCommandGroupKey.Factory.asKey('profiles-command'))
        this.profileService = profileService
    }

    @Override
    protected Observable<List<Profile>> construct() {
        profileService.getProfiles()
    }
}