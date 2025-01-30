package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

public class TipModel {
    long id;
    String tip;
    String tipCateg;

    public TipModel(long id, String tip) {
        id = id;
        tip = tip;
    }

    public TipModel(long id, String tip, String tipCateg) {
        id = id;
        tip = tip;
        tipCateg = tipCateg;
    }

    public long getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

    public String getTipCateg(){
        return tipCateg;
    }
}
