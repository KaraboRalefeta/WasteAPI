package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class AbstractRepo {

    protected Statement st;
    protected Connection con;
    public AbstractRepo(){

        String url = "jdbc:h2:file:./main/resources/database/wasteDatabase.db";
        String username = "sa";
        String password = "password";

        try{
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch (Exception e){
            //catch properly
            e.printStackTrace();
        }
    }
    public AbstractRepo(String url, String username, String password){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
