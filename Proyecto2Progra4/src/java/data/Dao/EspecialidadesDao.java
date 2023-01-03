//#Proyecto #2 Sistema de Expediente Electronico
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
import logic.Especialidad;

public class EspecialidadesDao {
    
    public Especialidad from(ResultSet rs) {
        try {
            Especialidad r = new Especialidad();
            r.setEspecialidad(rs.getString("especialidad"));
            r.setId(rs.getInt("id_especialidad"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Especialidad> listarEspecialidades() throws SQLException, Exception {
        List<Especialidad> sala = new ArrayList<>();
        String sql = "select * from especialidades";
        PreparedStatement stm = Database.instance().prepareStatement(sql);

        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            sala.add(from(rs));

        }
        if (sala.isEmpty()) {
            throw new Exception();
        }
        return sala;

    }
          public void create(String especi) throws Exception {
        String sql = "insert into especialidades (especialidad)"
                + "values(?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, especi);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("La Especialidad ya existe");
        }
        
    }
         public Especialidad traerEspecialidad(int id) throws Exception{
        String sql="select * from especialidades where id_especialidad=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("La Especialidad no Existe");
        }
    }
         
    public boolean consultarEspecialidad(int id) throws Exception {

        String sql = "SELECT id_especialidad FROM especialidades WHERE id_especialidad = ?; ";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            if (id==rs.getInt("id_especialidad")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    
    
    
}
