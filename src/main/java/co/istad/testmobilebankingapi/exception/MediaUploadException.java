package co.istad.testmobilebankingapi.exception;

import co.istad.testmobilebankingapi.base.BaseError;
import co.istad.testmobilebankingapi.base.BasedErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

public class MediaUploadException {
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSize;
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    BasedErrorResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        BaseError<String> baseError = BaseError.<String>builder()
                .code(HttpStatus.PAYLOAD_TOO_LARGE.getReasonPhrase())
                .description("Media upload size maxiumum is: " + maxSize)
                .build();
        return new BasedErrorResponse(baseError);
    }
}
