package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler;

public class ApiResponse <Cls>{
    int status;
    Cls body;
    public ApiResponse(int status, Cls body){
        this.status = status;
        this.body = body;
    }

    public Cls getBody() {
        return body;
    }

    public int getStatus() {
        return status;
    }
}
