package co.istad.testmobilebankingapi.exception;

import co.istad.testmobilebankingapi.base.BaseError;
import co.istad.testmobilebankingapi.base.BasedErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleServiceErrors(ResponseStatusException ex) {

        BaseError<String> baseError = new BaseError<>();
        baseError.setCode(ex.getStatusCode().toString());
        baseError.setDescription(ex.getReason());
        BasedErrorResponse basedErrorResponse = new BasedErrorResponse();
                basedErrorResponse.setError(baseError);

        return ResponseEntity.ok(basedErrorResponse);

    }

}

