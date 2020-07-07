package chadb.nuvalence.rectangles.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "chadb.nuvalence.rectangles")
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> badArgument(IllegalArgumentException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

}
