package net.cefaratez.usercloud.ps.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.ps.domain.Profile
import net.cefaratez.usercloud.ps.service.ProfileService
import rx.Observable

class GetProfileByIdCommand extends HystrixObservableCommand<Profile> {

    final ProfileService profileService
    final String profileId

    GetProfileByIdCommand(final ProfileService profileService,
                          final String profileId) {

        super(HystrixCommandGroupKey.Factory.asKey('profile-by-id-command'))
        this.profileService = profileService
        this.profileId = profileId
    }

    @Override
    protected Observable<Profile> construct() {
        profileService.getProfileById(profileId)
    }
}
