package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

public class CategoryModel {

    long id;
    String categ;

    public CategoryModel(long id, String categ) {
        id = id;
        categ = categ;
    }

    public long getId() {
        return id;
    }

    public String getCateg() {
        return categ;
    }
}
