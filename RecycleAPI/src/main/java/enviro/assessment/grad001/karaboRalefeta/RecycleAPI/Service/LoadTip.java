package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service;

import java.util.ArrayList;
import java.util.List;

public class LoadTip {
    public List<String> getAll(){
        return new ArrayList<>();
    }
    public String getByID(long id){
        return "id " + id;
    }

    public String getRandom(){
        return "random";
    }

    public void newTip(String tip){

    }
}
