package com.example.spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private AlbumType albumType;
    private ArtistSimplified[] artist; //vai retornar uma lista dos artistas
    private ExternalUrl externalUrl;
    private String id;
    private Image[] images;
    private String name;
    private String releaseData;
    private ModelObjectType type;
    private BigDecimal value; //aqui vamos simular o valor dos albuns
    }
