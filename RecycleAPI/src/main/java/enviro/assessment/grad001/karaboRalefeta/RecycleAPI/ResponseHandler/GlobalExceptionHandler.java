package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleCustomException(CustomException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ex.getStatus(), error));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse("An unexpected error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, error));
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleURLException(Exception ex) {
        ErrorResponse error = new ErrorResponse("URL is invalid");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, error));
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleRequestMethodException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Request method is not support for current url");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), error));
    }
}

