package com._5.SimpleNotatnik.controller;

import com._5.SimpleNotatnik.dto.MediaDto;
import com._5.SimpleNotatnik.model.Media;
import com._5.SimpleNotatnik.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaRepository mediaRepository;

    @GetMapping
    public List<MediaDto> getAllMedia() {
        return mediaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaDto> uploadMedia(@RequestPart("file") MultipartFile file,
                                                @RequestPart(value = "title", required = false) String title,
                                                @RequestPart(value = "description", required = false) String description) throws IOException {
        Media media = Media.builder()
                .title(title == null ? file.getOriginalFilename() : title)
                .description(description)
                .filename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .data(file.getBytes())
                .build();

        Media saved = mediaRepository.save(media);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadMedia(@PathVariable Long id) {
        return mediaRepository.findById(id)
                .map(m -> {
                    String contentType = m.getContentType() == null ? "application/octet-stream" : m.getContentType();
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + (m.getFilename() == null ? "file" : m.getFilename()) + "\"")
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(m.getData());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private MediaDto toDto(Media m) {
        return MediaDto.builder()
                .id(m.getId())
                .title(m.getTitle())
                .description(m.getDescription())
                .creationDate(m.getCreationDate())
                .modifiedDate(m.getModifiedDate())
                .filename(m.getFilename())
                .contentType(m.getContentType())
                .build();
    }
}
