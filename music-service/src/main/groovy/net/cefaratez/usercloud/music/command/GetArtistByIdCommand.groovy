package net.cefaratez.usercloud.music.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.music.domain.Artist
import net.cefaratez.usercloud.music.service.ArtistService
import rx.Observable

class GetArtistByIdCommand extends HystrixObservableCommand<Artist> {

    final ArtistService artistService
    final String artistId

    GetArtistByIdCommand(final ArtistService artistService,
                         final String artistId) {
        super(HystrixCommandGroupKey.Factory.asKey('artist-by-id-command'))
        this.artistService = artistService
        this.artistId = artistId
    }

    @Override
    protected Observable<Artist> construct() {
        artistService.getArtistById(artistId)
    }
}
