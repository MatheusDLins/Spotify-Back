package com.example.spotify.service.integration;

import ch.qos.logback.core.net.server.Client;
import com.example.spotify.config.SpotifyConfig;
import com.example.spotify.dto.AlbumDto;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyApiClient {

    private final SpotifyConfig spotifyConfig;
    private SpotifyApi spotifyApi;

    private SpotifyApi getSpotifyApi() {
        if (spotifyApi == null) {
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(spotifyConfig.getClientId())
                    .setClientSecret(spotifyConfig.getClientSecret())
                    .build();
        }
        return spotifyApi;
    }

    public List<AlbumDto> getAlbums(String search) {
        try {
            log.info("Pegando álbuns do Spotify");
            getSpotifyApi().setAccessToken(getAccessToken());
            var albums = getSpotifyApi().searchAlbums(search)
                    .market(CountryCode.BR)
                    .limit(30)
                    .build()
                    .execute()
                    .getItems();

            List<AlbumDto> albumDtos = new java.util.ArrayList<>();
            for (var album : albums) {
                albumDtos.add(AlbumDto.builder()
                        .albumType(album.getAlbumType())
                        .artist(album.getArtists())
                        .externalUrl(album.getExternalUrls())
                        .id(album.getId())
                        .images(album.getImages())
                        .name(album.getName())
                        .releaseData(album.getReleaseDate())
                        .type(album.getType())
                        .value(BigDecimal.valueOf(Math.random() * ((100.00 - 12.00) + 1) + 12.00).setScale(2, RoundingMode.UP))
                        .build());
            }
            return albumDtos;

        } catch (IOException | ParseException | SpotifyWebApiException ex) {
            log.error("Erro ao tentar pegar álbuns", ex);
            throw new RuntimeException(ex);
        }
    }

    private String getAccessToken() throws IOException, ParseException, SpotifyWebApiException {
        ClientCredentialsRequest clientCredentialsRequest = getSpotifyApi().clientCredentials().build();
        return clientCredentialsRequest.execute().getAccessToken();
    }
}
