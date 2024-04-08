package co.istad.testmobilebankingapi.features.media;

import co.istad.testmobilebankingapi.features.media.dto.MediaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file, String folderName);

    List<MediaResponse> uploadMulitple(List<MultipartFile> files, String folderName);
    MediaResponse loadMediaByName(String mediaName, String folderName);
    MediaResponse deleteMediaByName(String mediaName, String folderName);
    List<MediaResponse> loadAllMedias(String folderName);
    Resource downloadMediaByName(String mediaName, String folderName);
}
