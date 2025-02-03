package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Repository;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.TipModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.CustomException;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipRepository extends AbstractRepo{
    public TipRepository(){
        try{
            boolean execute = st.execute("CREATE TABLE IF NOT EXISTS TipDB (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    tip TEXT UNIQUE\n" +
                    ");\n");
        }catch (SQLException e){
            throw new CustomException("Tip database failed", 500);
        }
    }

    public List<TipModel> getAllTips(){
        ArrayList<TipModel> tipList = new ArrayList<>();
        try{
            ResultSet rs = st.executeQuery("SELECT * FROM TipD");
            while (rs.next()){
                long id = rs.getLong(1);
                String tip = rs.getString(2);
                tipList.add(new TipModel(id,tip));
            }
        }catch (SQLException e){
            throw new CustomException("Tip database failed", 500);
        }
        if (tipList.isEmpty()){
            throw new CustomException("Currently no tips in the system", HttpStatus.NO_CONTENT.value());
        }
        return tipList;
    }
    public TipModel getByID(long id){
        String sql = "SELECT * FROM TIPDB WHERE id="+id;
        try{
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                return new TipModel(id, rs.getString("tip"));
            }
        } catch (SQLException e) {
            throw new CustomException("Tip database failed", 500);
        }
        throw new CustomException("No Tip with such ID", 404);
    }
    public TipModel addTip(String tip){
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM TipDB WHERE tip=?");
            ps.setString(1, tip);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return new TipModel(rs.getLong(1), rs.getString(2));
            }

            ps = con.prepareStatement("INSERT INTO TipDB(tip) VALUES (?);");
            ps.setString(1, tip);
            ps.execute();
            rs = st.executeQuery("SELECT * FROM CategDB WHERE tip="+"\""+tip+"\"");
            while(rs.next()){
                return new TipModel(rs.getLong(1), rs.getString(2));
            }

        } catch (SQLException e) {
            throw new CustomException("Tip database failed", 500);
        }
        throw new RuntimeException();
    }
    public void deleteTip(long id){
        String sql = "DELETE FROM tipDb WHERE id=?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ps.execute();
        } catch (SQLException e) {
            throw new CustomException("Tip database failed", 500);
        }
    }
    public TipModel editTip(long id, String tip){
        // change the details of a tip, but not the id of the tip
        try{
            String sql = "UPDATE tipDB SET tip = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tip);
            ps.setLong(2, id);
            ps.execute();
            return getByID(id);
        } catch (SQLException e) {
            throw new CustomException("Tip database failed", 500);
        }
    }
}
