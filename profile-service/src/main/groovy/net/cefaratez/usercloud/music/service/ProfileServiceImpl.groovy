package net.cefaratez.usercloud.music.service

import net.cefaratez.usercloud.music.domain.Profile
import net.cefaratez.usercloud.music.repository.ProfileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ratpack.exec.Blocking
import rx.Observable

@Service
class ProfileServiceImpl implements ProfileService {

    final ProfileRepository profileRepository

    @Autowired
    ProfileServiceImpl(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository
    }

    @Override
    Observable<List<Profile>> getProfiles() {
        Blocking.get {
            profileRepository.findAll()
        }.observe()
    }

    @Override
    Observable<Profile> getProfileById(String id) {
        Blocking.get {
            profileRepository.findOne(id)
        }.observe()
    }

    @Override
    Observable<Profile> saveProfile(Profile profile) {
        Blocking.get {
            profileRepository.save(profile)
        }.observe()
    }
}
