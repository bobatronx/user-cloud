package net.cefaratez.usercloud.music.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.music.domain.Artist
import net.cefaratez.usercloud.music.service.ArtistService
import rx.Observable

class GetArtistByNameCommand extends HystrixObservableCommand<Artist> {

    final ArtistService artistService
    final String artistName

    GetArtistByNameCommand(final ArtistService artistService,
                           final String artistName) {

        super(HystrixCommandGroupKey.Factory.asKey('artist-by-name-command'))
        this.artistService = artistService
        this.artistName = artistName
    }

    @Override
    protected Observable<Artist> construct() {
        artistService.getArtistByName(artistName)
    }
}