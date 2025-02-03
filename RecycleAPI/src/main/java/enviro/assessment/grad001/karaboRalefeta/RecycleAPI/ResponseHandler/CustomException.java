package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler;

public class CustomException extends RuntimeException {
    int status = 400;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

