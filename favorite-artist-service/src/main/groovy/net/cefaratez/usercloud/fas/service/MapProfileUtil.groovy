package net.cefaratez.usercloud.music.service

import net.cefaratez.usercloud.music.domain.ProfileWithArtist

/**
 * Created by James Cefaratti
 * 10/17/15.
 */
class MapProfileUtil {

    static mapProfileResponseToProfile(def response) {

        new ProfileWithArtist(firstName: response.firstName,
                              lastName: response.lastName,
                              favoriteArtist: response.favoriteArtist,
                              emailAddress: response.contactInformation.emailAddress,
                              phoneNumber: response.contactInformation.phoneNumber,
                              street: response.address.street,
                              city: response.address.city,
                              state: response.address.state,
                              zip: response.address.zip)
    }

    static mapArtistResponseToProfile(final ProfileWithArtist profileWithArtist, def response) {
        profileWithArtist.albums = response.albums
        profileWithArtist
    }
}
