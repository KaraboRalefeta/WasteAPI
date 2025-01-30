package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.CategoryModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class CategoryRepository {
    String url = "jdbc:h2:file:./src/main/resources/database/wasteDatabase.db";
    String username = "sa";
    String password = "password";
    Statement st;
    public CategoryRepository(){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch (Exception e){
            //catch properly
            System.out.println("didn't work");
            e.printStackTrace();
        }
    }

    public List<CategoryModel> getAllCategory(){
        // returns a list of all tips in the model format
        return null;
    }
    public CategoryModel getByID(long id){
        // returns a specific tip based off the id
        return null;
    }
    public long addCategory(String categ){
        // service should split the info for this part to work efficiently
        // make sure that the tip is added and return the id of the tip or -1 if it wasn't added
        return -1;
    }
    public void deleteCategory(long id){
        // delete categ by id
        // and all the Items under it;

    }
    public void editCategory(long categID){
        // change the details of a Category, but not the id of the Category
    }

}
