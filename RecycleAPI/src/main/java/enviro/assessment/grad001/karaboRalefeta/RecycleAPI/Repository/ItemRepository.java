package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.ItemModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ItemRepository {
    String url = "jdbc:h2:file:./src/main/resources/database/wasteDatabase.db";
    String username = "sa";
    String password = "password";
    Statement st;
    public ItemRepository(){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch (Exception e){
            //catch properly
            System.out.println("didn't work");
            e.printStackTrace();
        }
    }
    public ItemModel getByID(long id){
        // returns a specific tip based off the id
        return null;
    }
    public long addItem(long categID, String Item){
        // service should split the info for this part to work efficiently
        // make sure that the Item is added and return the id of the Item or -1 if it wasn't added
        return -1;
    }
    public void deleteItem(long categID, long itemID){
        // delete Item by id
        // and all the Items under it;
    }

}
