package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.CategoryModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.ItemModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository.CategoryRepository;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository.ItemRepository;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    ItemRepository ir = new ItemRepository();
    CategoryRepository cr = new CategoryRepository();

    public List<CategoryModel> getAll(){
        List<CategoryModel> categs = cr.getAllCategory();
        ArrayList<CategoryModel> newCategs = new ArrayList<>();
        for (CategoryModel categ: categs){
            newCategs.add(new CategoryModel(categ, getAllItems(categ.getName())));
        }
        return newCategs;
    }
    public List<ItemModel> getAllItems(String categ){
        long id = cr.addCategory(categ).getId();
        List<ItemModel> all = ir.getByField("categ_id", String.valueOf(id));
        ArrayList<ItemModel> items = new ArrayList<>();

        for (ItemModel im:all){
            if (im.getCateg() == cr.addCategory(categ).getId()){
                items.add(im);
            }
        }
        if (items.isEmpty()){
            throw new CustomException("Currently no items under that category in the system", HttpStatus.NO_CONTENT.value());
        }
        return items;
    }
    public CategoryModel getByID(long id){
        CategoryModel categ = cr.getByID(id);
        return new CategoryModel(categ, getAllItems(categ.getName()));
    }
    public CategoryModel getByName(String name){
        CategoryModel categ = cr.addCategory(name);
        return new CategoryModel(categ, getAllItems(categ.getName()));

    }

    public CategoryModel editCategory(long id, JSONObject body){
        if (!body.has("name")){
            throw new CustomException("Request body is missing \"name\" key");
        }
        if (body.has("id")){
            throw new CustomException("Request body is must not have \"id\" key");
        }
        cr.editCategory(id, body.getString("name"));
        CategoryModel categ = getByID(id);
        return new CategoryModel(categ, getAllItems(categ.getName()));
    }
    public void deleteCategory(long id){
        cr.deleteCategory(id);
        ir.deleteItemsWithCateg(id);
    }

    public CategoryModel newCategory(JSONObject body){
        if (!body.has("name")){
            throw new CustomException("Request body is missing \"name\" key");
        }
        CategoryModel categ = cr.addCategory(body.getString("name"));
        return new CategoryModel(categ, getAllItems(categ.getName()));
    }
}
