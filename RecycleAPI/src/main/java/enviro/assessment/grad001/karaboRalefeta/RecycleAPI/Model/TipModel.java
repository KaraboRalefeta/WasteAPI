package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

public class TipModel {
    long id;
    String tip;

    public TipModel(long id, String tip) {
        this.id = id;
        this.tip = tip;
    }

    public long getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

}
