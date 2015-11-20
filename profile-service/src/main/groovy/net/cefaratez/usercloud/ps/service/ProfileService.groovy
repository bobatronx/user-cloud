package net.cefaratez.usercloud.ps.service

import net.cefaratez.usercloud.ps.domain.Profile
import rx.Observable

interface ProfileService {
    Observable<List<Profile>> getProfiles()
    Observable<Profile> getProfileById(String id)
    Observable<Profile> saveProfile(Profile profile)
}
