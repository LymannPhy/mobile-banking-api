package co.istad.testmobilebankingapi.features.media;

import co.istad.testmobilebankingapi.features.media.dto.MediaResponse;
import co.istad.testmobilebankingapi.util.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService{
    @Value("${media.server-path}")
    String serverPath;
    @Value("${media.base-uri}")
    String baseUri;

    @Override

    public MediaResponse uploadSingle(MultipartFile file, String folderName) {
        // Generate new unique name for file upload
        String newName = UUID.randomUUID().toString();

        // Extract extension from file upload
        // Assume profile.png
        int lastDotIndex = file.getOriginalFilename()
                .lastIndexOf(".");
        String extension = file.getOriginalFilename()
                .substring(lastDotIndex + 1);
        newName = newName + "." + extension;

        // Copy file to server
        Path path = Paths.get(serverPath + folderName + "\\" + newName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();
    }

    @Override
    public List<MediaResponse> uploadMulitple(List<MultipartFile> files, String folderName) {
        //Create empty array list
        List<MediaResponse> mediaResponses = new ArrayList<>();
        //Upload loop to each
        files.forEach(file -> {
                    MediaResponse mediaResponse = this.uploadSingle(file, folderName);
                    mediaResponses.add(mediaResponse);
                });
        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {
        //Create absolute part
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try{
            Resource resource = new UrlResource(path.toUri());
            log.info("Load Resource: {}", resource.getFilename());
            if(!resource.exists()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media has not been foud!");
            }
            return MediaResponse.builder()
                    .name(resource.getFilename())
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                    .build();
        }
        catch(MalformedURLException e){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        //Create absolute part
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            Long size = Files.size(path);
            if(Files.deleteIfExists(path)){
                return MediaResponse.builder()
                        .name(mediaName)
                        .contentType(Files.probeContentType(path))
                        .extension(MediaUtil.extractExtension(mediaName))
                        .size(size)
                        .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                        .build();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Media has not been found!");
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
    }
    @Override
    public List<MediaResponse> loadAllMedias(String folderName) {
        // Create the absolute path to the media folder
        Path mediaFolderPath = Paths.get(serverPath + folderName);
        try {
            // List all files in the media folder
            List<Path> mediaFiles = Files.list(mediaFolderPath)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            List<MediaResponse> mediaResponses = new ArrayList<>();

            for (Path mediaFile : mediaFiles) {
                MediaResponse mediaResponse = MediaResponse.builder()
                        .name(mediaFile.getFileName().toString())
                        .contentType(Files.probeContentType(mediaFile))
                        .extension(MediaUtil.extractExtension(mediaFile.getFileName().toString()))
                        .size(Files.size(mediaFile))
                        .uri(String.format("%s%s/%s", baseUri, folderName, mediaFile.getFileName().toString()))
                        .build();
                mediaResponses.add(mediaResponse);
            }

            return mediaResponses;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Resource downloadMediaByName(String mediaName, String folderName) {
        // Create absolute path of media
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Media has not been found!");
        }
    }

}
