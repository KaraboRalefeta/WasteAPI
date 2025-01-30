package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.TipModel;

import java.util.ArrayList;
import java.util.List;

public class TipService {
    public List<TipModel> getAll(){
        return new ArrayList<>();
    }
    public String getByID(long id){
        return "id " + id;
    }

    public String getRandom(){
        return "random";
    }
    public void updateTip(long id, String newDetails){

    }
    public void deleteTip(long id){

    }

    public void newTip(String tip){

    }
}
