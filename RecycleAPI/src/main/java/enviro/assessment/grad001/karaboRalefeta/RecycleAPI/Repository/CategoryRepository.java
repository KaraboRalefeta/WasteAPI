package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.CategoryModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends AbstractRepo{
    public CategoryRepository(){
        try{
            boolean execute = st.execute("CREATE TABLE IF NOT EXISTS CategDB (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name CHAR(30) UNIQUE\n" +
                    ");\n");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<CategoryModel> getAllCategory(){
        ArrayList<CategoryModel> catList = new ArrayList<>();
        try{
            ResultSet rs = st.executeQuery("SELECT * FROM CategDB");
            while (rs.next()){
                long id = Long.valueOf(rs.getString(1));
                String name = rs.getString(2);
                catList.add(new CategoryModel(id,name));
            }
        }catch (SQLException e){
            throw new CustomException("Category database seems to have an issue", 500);
        }
        return catList;
    }
    public CategoryModel getByID(long id){
        String sql = "Select * FROM CategDB WHERE id=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new CategoryModel(id, rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new CustomException("Category database seems to have an issue", 500);
        }
        throw new CustomException("No category with such name/id exists in database", 404);
    }
    public CategoryModel addCategory(String categ){
        try{
            ResultSet rs = st.executeQuery("SELECT * FROM CategDB");
            Long id = null;
            while(rs.next()){
                if(rs.getString(2) == categ){
                    id = rs.getLong(1);
                }
            }
            if (id != null){
                return getByID(id);
            }
            PreparedStatement ps = con.prepareStatement("INSERT INTO CategDB (name) VALUES (?);");
            ps.setString(1, categ);
            ps.execute();
            rs = st.executeQuery("SELECT * FROM CategDB WHERE="+categ);
            CategoryModel cm = null;
            while(rs.next()){
                cm = getByID( rs.getLong(1));
            }
            return cm;


        } catch (SQLException e) {
            throw new CustomException("Category database seems to have an issue", 500);        }
    }
    public void deleteCategory(long id){
        String sql = "DELETE FROM CategDB WHERE id=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ps.execute();
        } catch (SQLException e) {
            throw new CustomException("Category database seems to have an issue", 500);        }
    }
    public void editCategory(long categID, String categ){
        try{
            String sql = "UPDATE CategDB SET name = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categ);
            ps.setString(2, String.valueOf(categID));
            ps.execute();
        } catch (SQLException e) {
            throw new CustomException("Category database seems to have an issue", 500);        }
    }

    public static void main(String[] args) {
        CategoryRepository cr = new CategoryRepository();
        CategoryModel byID = cr.getByID(100);
        System.out.println(byID);
    }
}
