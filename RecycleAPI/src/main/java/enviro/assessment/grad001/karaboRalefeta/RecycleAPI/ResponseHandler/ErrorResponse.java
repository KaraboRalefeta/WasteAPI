package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler;

public class ErrorResponse {
    private String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }
}

