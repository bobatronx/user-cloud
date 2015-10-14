package net.cefaratez.usercloud.command

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixObservableCommand
import net.cefaratez.usercloud.domain.Artist
import net.cefaratez.usercloud.service.ArtistService
import rx.Observable

class SaveArtistCommand extends HystrixObservableCommand<Artist> {

    final ArtistService artistService
    final Artist artist

    SaveArtistCommand(final ArtistService artistService,
                      final Artist artist) {

        super(HystrixCommandGroupKey.Factory.asKey('save-artist-command'))
        this.artistService = artistService
        this.artist = artist
    }

    @Override
    protected Observable<Artist> construct() {
        artistService.saveArtist(artist)
    }
}
