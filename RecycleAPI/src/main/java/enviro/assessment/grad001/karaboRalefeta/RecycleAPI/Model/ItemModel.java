package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model;

public class ItemModel {
    long id;
    String item;
    long categ;
    String impact;
    boolean recyclable;
    String alternative;
    String instrutions;

    public ItemModel(long id, String item, long categ, String impact, boolean recyclable,String instrutions, String alternative) {
        this.id = id;
        this.item = item;
        this.categ = categ;
        this.impact = impact;
        this.recyclable = recyclable;
        this.instrutions = instrutions;
        this.alternative = alternative;
    }

    public long getId() {
        return id;
    }

    public long getCateg() {
        return categ;
    }

    public String getItem(){
        return item;
    }
    public String getImpact(){return impact;}
    public boolean isRecyclable(){return recyclable;}
    public String getAlternative(){return alternative;}

    public String getInstrutions() {
        return instrutions;
    }
}
