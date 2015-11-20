package net.cefaratez.usercloud.ps.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.ps.service.ProfileService
import net.cefaratez.usercloud.ps.domain.Profile
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
