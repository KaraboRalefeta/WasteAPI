package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

public class ItemModel {
    long id;
    String item;
    String categ;

    public ItemModel(long id, String item) {
        id = id;
        item = item;
    }

    public ItemModel(long id, String item, String categ) {
        id = id;
        item = item;
        categ = categ;
    }

    public long getId() {
        return id;
    }

    public String getCateg() {
        return categ;
    }

    public String getItem(){
        return item;
    }
}
