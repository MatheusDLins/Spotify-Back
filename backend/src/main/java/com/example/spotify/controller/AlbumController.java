package com.example.spotify.controller;

import com.example.spotify.dto.AlbumDto;
import com.example.spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    public final AlbumService albumService;

    @GetMapping("/all")
    public ResponseEntity<List<AlbumDto>> getAllAlbums(@RequestParam("search") String search){
        return ResponseEntity.ok(albumService.getAlbums(search));
    }

}
