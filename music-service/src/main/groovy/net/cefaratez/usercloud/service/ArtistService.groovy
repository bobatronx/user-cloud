package net.cefaratez.usercloud.service

import net.cefaratez.usercloud.domain.Artist
import rx.Observable

interface ArtistService {
    Observable<List<Artist>> getArtists()
    Observable<Artist> getArtistById(String artistId)
    Observable<Artist> getArtistByName(String name)
    Observable<Artist> saveArtist(Artist artist)
}
