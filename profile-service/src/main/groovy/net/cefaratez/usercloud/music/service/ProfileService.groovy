package net.cefaratez.usercloud.music.service

import net.cefaratez.usercloud.music.domain.Profile
import rx.Observable

interface ProfileService {
    Observable<List<Profile>> getProfiles()
    Observable<Profile> getProfileById(String id)
    Observable<Profile> saveProfile(Profile profile)
}
