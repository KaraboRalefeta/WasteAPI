package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.ItemModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemRepository extends AbstractRepo{

    public ItemRepository(){
        try{
            boolean execute = st.execute("CREATE TABLE IF NOT EXISTS ItemDb (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name CHAR(30) UNIQUE,\n" +
                    "    Categ_ID INT,\n"+
                    "    impact TEXT,\n" +
                    "    recycling_Instr TEXT,\n" +
                    "    recyclable BOOLEAN,\n" +
                    "    reuse_Instr TEXT,\n" +
                    "    FOREIGN KEY (Categ_ID) REFERENCES CategDB(id)"+
                    ");\n");
            System.out.println("worked? :"+ execute);
        }catch (SQLException e){
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }
    }
    public List<ItemModel> getAll(){
        ArrayList<ItemModel> all = new ArrayList<>();
        String sql = "Select * FROM ItemDb";
        try{
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                long id = rs.getLong("id");
                String item = rs.getString("name");
                long categID = rs.getLong("categ_ID");
                String impact = rs.getString("impact");
                String instru = rs.getString("recycling_Instr");
                boolean recyclable = rs.getBoolean("recyclable");
                String reuse_Instr = rs.getString("reuse_Instr");

                all.add(new ItemModel(id, item, categID, impact, recyclable, instru, reuse_Instr));
            }
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }
        if (all.isEmpty()){
            throw new CustomException("Currently no items with such a name in the system", HttpStatus.NO_CONTENT.value());
        }
        return all;
    }
    public List<ItemModel> getByField(String field, String value){
        ArrayList<ItemModel> all = new ArrayList<>();
        String sql = "Select * FROM ItemDb WHERE "+field+"="+value+" ;";
        try{
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                long id = rs.getLong("id");
                String item = rs.getString("name");
                long categID = rs.getLong("categ_ID");
                String impact = rs.getString("impact");
                String instru = rs.getString("recycling_Instr");
                boolean recyclable = rs.getBoolean("recyclable");
                String reuse_Instr = rs.getString("reuse_Instr");

                all.add(new ItemModel(id, item, categID, impact, recyclable, instru, reuse_Instr));
            }
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }
        return all;
    }

    public ItemModel getByID(long id){
        // returns a specific tip based off the id
        String sql = "Select * FROM ItemDb WHERE id=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            ItemModel itemModel;
            while (rs.next()){
                String item = rs.getString("name");
                long categID = rs.getLong("categ_ID");
                String impact = rs.getString("impact");
                String instru = rs.getString("recycling_Instr");
                boolean recyclable = rs.getBoolean("recyclable");
                String reuse_Instr = rs.getString("reuse_Instr");

                itemModel = new ItemModel(id, item, categID, impact, recyclable, instru, reuse_Instr);
                return itemModel;
            }
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }catch (JSONException e){
            throw new CustomException("Request body seems to have an issue");
        }
        throw new CustomException("Found no item with such an ID", 404);
    }
    public ItemModel addItem(JSONObject body){
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ItemDB WHERE name=? and categ_Id=?");
            ps.setString(1,body.getString("name"));
            ps.setLong(2,body.getLong("categ_id"));

            ResultSet rs = ps.executeQuery();

            Long id;
            while(rs.next()){
                    id = rs.getLong(1);
                    return getByID(id);
            }
            ps = con.prepareStatement("INSERT INTO ItemDB (name, Categ_ID, impact, recycling_Instr, recyclable, reuse_Instr) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            ps.setString(1,body.getString("name"));
            ps.setLong(2,body.getLong("categ_id"));
            ps.setString(3,body.getString("impact"));
            ps.setString(4,body.getString("recycling_instr"));
            ps.setBoolean(5,body.getBoolean("recyclable"));
            ps.setString(6,body.getString("reuse_instr"));
            ps.execute();

            ps = con.prepareStatement("SELECT * FROM ItemDB WHERE name=? and categ_Id=?");
            ps.setString(1,body.getString("name"));
            ps.setLong(2,body.getLong("categ_id"));

            rs = ps.executeQuery();
            id = null;
            return getByID(id);
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        } catch (JSONException e){
            throw new CustomException("Request body seems to have an issue");
        }
    }
    public ItemModel editItem(long itemID, JSONObject body){
        //edit details of the of item;
        try{

            for(String key:body.keySet()){
                PreparedStatement ps = con.prepareStatement("UPDATE itemDb SET "+ key +" = ? WHERE id = ?");
                ps.setString(1, body.get(key).toString());
                ps.setLong(2, itemID);
                ps.execute();
            }
            return getByID(itemID);
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        } catch (JSONException e){
            throw new CustomException("Request body seems to have an issue");
        }

    }
    public void deleteItem(long itemID){
        String sql = "DELETE FROM itemDb WHERE id=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(itemID));
            ps.execute();
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }
    }
    public void deleteItemsWithCateg(long categID){
        String sql = "DELETE FROM itemDb WHERE categ_ID=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(categID));
            ps.execute();
        } catch (SQLException e) {
            throw new CustomException("Item database seems to have an issue, this may be due to bad request or internal error");
        }
    }
}
