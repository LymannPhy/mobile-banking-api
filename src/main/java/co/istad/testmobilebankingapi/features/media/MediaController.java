package co.istad.testmobilebankingapi.features.media;

import co.istad.testmobilebankingapi.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) {
        return mediaService.uploadSingle(file, "IMAGES");
    }
    @PostMapping("/{upload-multiple}")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return mediaService.uploadMulitple(files, "IMAGES");
    }
    @GetMapping("/{mediaName}")
    MediaResponse loadMediaByName(@PathVariable String mediaName){
        return mediaService.loadMediaByName(mediaName, "IMAGES");
    }
    @DeleteMapping("/{mediaName}")
    MediaResponse deleteMediaByName(@PathVariable String mediaName){
        return mediaService.deleteMediaByName(mediaName, "IMAGES");
    }

    @GetMapping()
    List<MediaResponse> loadAllMedias() {
        return mediaService.loadAllMedias("IMAGES");
    }

    @GetMapping(path = "/download/{mediaName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<?> downloadMediaByName(@PathVariable String mediaName) {
        Resource resource = mediaService.downloadMediaByName(mediaName, "IMAGES");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + mediaName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
