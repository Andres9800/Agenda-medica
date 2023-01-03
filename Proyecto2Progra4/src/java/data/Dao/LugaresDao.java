//#Proyecto #12 Sistema de Expediente Electronico
//#-Wendy Largaespada Aragon
//#-Andres Rojas Rojas
//#-Andres Cordero Gutierrez
package data.Dao;

import data.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Lugar;


public class LugaresDao {
    
        public Lugar from(ResultSet rs) {
        try {
            Lugar r = new Lugar();
            r.setLugar(rs.getString("lugar"));
            r.setId(rs.getInt("id_lugares"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Lugar> listarLugares() throws SQLException, Exception {
        List<Lugar> lugar = new ArrayList<>();
        String sql = "select * from lugares";
        PreparedStatement stm = Database.instance().prepareStatement(sql);

        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            lugar.add(from(rs));

        }
        if (lugar.isEmpty()) {
            throw new Exception();
        }
        return lugar;

    }
          public void create(String especi) throws Exception {
        String sql = "insert into lugares (lugar)"
                + "values(?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, especi);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El Lugar ya existe");
        }
        
    }
         public Lugar traerLugar(int id) throws Exception{
        String sql="select * from lugares where id_lugares=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("El Lugar no Existe");
        }
    }
         
    public boolean consultarLugar(int id) throws Exception {

        String sql = "SELECT id_lugares FROM lugares WHERE id_lugares = ?; ";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            if (id==rs.getInt("id_lugares")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    
    
    
}
