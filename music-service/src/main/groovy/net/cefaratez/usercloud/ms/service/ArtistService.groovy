package net.cefaratez.usercloud.ms.service

import net.cefaratez.usercloud.ms.domain.Artist
import rx.Observable

interface ArtistService {
    Observable<List<Artist>> getArtists()
    Observable<Artist> getArtistById(String artistId)
    Observable<Artist> getArtistByName(String name)
    Observable<Artist> saveArtist(Artist artist)
}
