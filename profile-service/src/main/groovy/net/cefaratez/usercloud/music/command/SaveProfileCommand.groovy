package net.cefaratez.usercloud.music.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.music.domain.Profile
import net.cefaratez.usercloud.music.service.ProfileService
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
