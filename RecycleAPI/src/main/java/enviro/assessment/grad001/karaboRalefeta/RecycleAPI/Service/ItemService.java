package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.ItemModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository.ItemRepository;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    ItemRepository ir = new ItemRepository();
    public List<ItemModel> getAll(){
        List<ItemModel> all = ir.getAll();
        return all;
    }
    public ItemModel getRandom(){
        List<ItemModel> all = getAll();
        int index = (int)(Math.random() * all.size());
        return all.get(index);
    }
    public ItemModel getByID(long id){
        return ir.getByID(id);
    }
    public List<ItemModel> getByName(String name){
        ArrayList<ItemModel> all = new ArrayList<>();
        for (ItemModel i: ir.getAll()){
            if (i.getItem().equalsIgnoreCase(name)){
                all.add(i);
            }
        }
        return all;
    }

    public ItemModel editItem(long id, JSONObject body){
        if (body.has("id")){
            throw new CustomException("Request body is must not have \"id\" key", HttpStatus.BAD_REQUEST.value());
        }
        return ir.editItem(id, body);
    }
    public void deleteItem(long id){
        ir.deleteItem(id);
    }

    public ItemModel newItem(JSONObject body){
        return ir.addItem(body);
    }
}
