package net.cefaratez.usercloud.music.service

import net.cefaratez.usercloud.music.domain.Artist
import rx.Observable

interface ArtistService {
    Observable<List<Artist>> getArtists()
    Observable<Artist> getArtistById(String artistId)
    Observable<Artist> getArtistByName(String name)
    Observable<Artist> saveArtist(Artist artist)
}
