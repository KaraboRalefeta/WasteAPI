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
        // service should split the info for this part to work efficiently
        // make sure that the Item is added and return the id of the Item or -1 if it wasn't added

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

    public static void main(String[] args) {
        ItemRepository ir = new ItemRepository();
        JSONObject js = new JSONObject();

        js.put("name", "bottle");
        js.put("categ_id", 1);
        js.put("impact", "Plastic bottles take up significant space in landfills, which are already overflowing. As they decompose, they release harmful chemicals into the soil and groundwater, contaminating these vital resources.");
        js.put("recycling_instr", "Empty it: Make sure the bottle is completely empty. Any remaining liquids can contaminate other recyclables.\n" +
                "Rinse it: Give the bottle a quick rinse to remove any residue. This helps prevent odors and keeps the recycling process clean.\n" +
                "Replace the cap: It's now generally recommended to leave the cap on the bottle. This ensures that both the bottle and the cap get recycled together, as they are often made from different types of plastic.\n" +
                "Don't crush it: While it might seem like a good idea to crush the bottle to save space, this can actually hinder the recycling process. It's best to leave the bottle in its original shape.");
        js.put("recyclable", true);
        js.put("reuse_instr", "Watering Plants: Plastic bottles make excellent watering cans for indoor or outdoor plants. You can even poke small holes in the cap for a gentle sprinkle.\n" +
                "Storing Non-Food Items: Use them to store craft supplies, small toys, hardware, or other non-food items. They're great for organizing drawers or shelves.\n" +
                "Cleaning Supplies: Dilute cleaning solutions and store them in labeled plastic bottles. This is especially handy for smaller spray bottles.\n" +
                "Pet Water Bowls: Larger bottles can be cut down and used as temporary water or food bowls for pets. \n" +
                "Seed Starting: Cut the bottom off and use the bottle as a mini-greenhouse to start seeds.\n" +
                "Travel Containers: For non-food items like shampoo or soap (especially travel sizes), plastic bottles can be handy, but always clearly label them.");
//        ir.addItem(js);
//        JSONObject json = new JSONObject();
//        json.put("recyclable", "true");
//        ir.editItem(1, json);


    }

}
