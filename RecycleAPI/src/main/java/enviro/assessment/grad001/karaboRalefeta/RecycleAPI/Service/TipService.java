package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.TipModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository.TipRepository;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;
import org.json.JSONObject;

import java.util.List;

public class TipService {
    TipRepository tip = new TipRepository();

    public List<TipModel> getAll(){
        return tip.getAllTips();
    }
    public TipModel getByID(long id){
        return tip.getByID(id);
    }

    public TipModel getRandom(){
        List<TipModel> all = getAll();

        int index = (int)(Math.random() * all.size());
        return all.get(index);
    }
    public TipModel editTip(long id, JSONObject body){
        if (!body.has("tip")){
            throw new CustomException("Request body is missing \"tip\" key");
        }
        if (body.has("id")){
            throw new CustomException("Request body is must not have \"id\" key");
        }
        return tip.editTip(id, body.getString("tip"));
    }
    public void deleteTip(long id){
        tip.deleteTip(id);
    }

    public TipModel addTip(JSONObject tipInfo){
        if (!tipInfo.has("tip")){
            throw new CustomException("Request body is missing \"tip\" key");
        }
        return tip.addTip(tipInfo.getString("tip"));
    }

}
