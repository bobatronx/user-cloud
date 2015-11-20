package net.cefaratez.usercloud.ps.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.ps.service.ProfileService
import net.cefaratez.usercloud.ps.domain.Profile
import rx.Observable

class SaveProfileCommand extends HystrixObservableCommand<Profile> {

    final ProfileService profileService
    final Profile profile

    SaveProfileCommand(final ProfileService profileService,
                       final Profile profile) {

        super(HystrixCommandGroupKey.Factory.asKey('save-profile-command'))
        this.profileService = profileService
        this.profile = profile
    }

    @Override
    protected Observable<Profile> construct() {
        profileService.saveProfile(profile)
    }
}
