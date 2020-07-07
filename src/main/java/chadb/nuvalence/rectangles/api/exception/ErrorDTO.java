package chadb.nuvalence.rectangles.api.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private int status;
    private String message;
}
