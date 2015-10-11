package net.cefaratez.usercloud.service

import net.cefaratez.usercloud.domain.Profile
import rx.Observable

interface ProfileService {
    Observable<List<Profile>> getProfiles()
    Observable<Profile> getProfileById(String id)
    Observable<Profile> saveProfile(Profile profile)
}
