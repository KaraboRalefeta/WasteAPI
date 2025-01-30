package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.TipModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class TipRepository {
    String url = "jdbc:h2:file:./src/main/resources/database/wasteDatabase.db";
    String username = "sa";
    String password = "password";
    Statement st;
    public TipRepository(){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch (Exception e){
            //catch properly
            System.out.println("didn't work");
            e.printStackTrace();
        }
    }

    public List<TipModel> getAllTips(){
        // returns a list of all tips in the model format
        return null;
    }
    public TipRepository getByID(long id){
        // returns a specific tip based off the id
        return null;
    }
    public long addTip(String tip, String categ){
        // service should split the info for this part to work efficiently
        // make sure that the tip is added and return the id of the tip or -1 if it wasn't added
        return -1;
    }
    public void deleteTip(long id){
        // delete tip by id
    }
    public void editTip(long id, String tip, String categ){
        // change the details of a tip, but not the id of the tip
    }

}
