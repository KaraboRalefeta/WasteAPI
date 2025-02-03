package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

import java.util.List;

public class CategoryModel {

    long id;
    String name;
    List<ItemModel> items;

    public CategoryModel(long id, String categ) {
        this.id = id;
        this.name = categ;
    }
    public CategoryModel(CategoryModel categModel, List<ItemModel> items) {
        this.name = categModel.getName();
        this.id = categModel.getId();
        this.items = items;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ItemModel> getItems() {
        return items;
    }
}
