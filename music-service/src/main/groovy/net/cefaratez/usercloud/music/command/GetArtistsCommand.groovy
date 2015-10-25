package net.cefaratez.usercloud.music.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.music.domain.Artist
import net.cefaratez.usercloud.music.service.ArtistService
import rx.Observable

class GetArtistsCommand extends HystrixObservableCommand<List<Artist>> {

    final ArtistService artistService

    GetArtistsCommand(final ArtistService artistService) {
        super(HystrixCommandGroupKey.Factory.asKey('artists-command'))
        this.artistService = artistService
    }

    @Override
    protected Observable<List<Artist>> construct() {
        artistService.getArtists()
    }
}
