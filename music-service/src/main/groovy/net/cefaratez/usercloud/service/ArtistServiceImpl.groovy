package net.cefaratez.usercloud.service

import net.cefaratez.usercloud.domain.Artist
import net.cefaratez.usercloud.repository.ArtistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ratpack.exec.Blocking
import rx.Observable

@Service
class ArtistServiceImpl implements ArtistService {

    final ArtistRepository artistRepository

    @Autowired
    ArtistServiceImpl(final ArtistRepository artistRepository) {
        this.artistRepository = artistRepository
    }

    @Override
    Observable<List<Artist>> getArtists() {
        Blocking.get {
            artistRepository.findAll()
        }.observe()

    }

    @Override
    Observable<Artist> getArtistById(String artistId) {
        Blocking.get {
            artistRepository.findOne(artistId)
        }.observe()
    }

    @Override
    Observable<Artist> getArtistByName(String name) {
        Blocking.get {
            artistRepository.findArtistByName(name)
        }.observe()
    }

    @Override
    Observable<Artist> saveArtist(Artist artist) {
        Blocking.get {
            artistRepository.save(artist)
        }.observe()
    }
}
