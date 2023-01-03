//#Proyecto #2 Sistema de Expediente Electronico
//#-Wendy Largaespada Aragon
//#-Andres Rojas Rojas
//#-Andres Cordero Gutierrez
package data.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Medico;
import logic.Slot;
import data.Database;


public class SlotsDao {
      public void create(int horainicio,int horafin, int dia, int medico) throws Exception{
        String sql="insert into slots (hora_inicio, hora_fin, dia, medico) "+
                "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, horainicio);
        stm.setInt(2, horafin);
        stm.setInt(3, dia);
        stm.setInt(4, medico);
        int count=Database.instance().executeUpdate(stm);
        if (count==0){
            throw new Exception("Slot ya existe");
        }
    }
    public boolean consultarSlot(int id) throws Exception{ // consulta slot por su id
       
       String sql="SELECT id_slots FROM slots WHERE id_slots = ?; ";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            if(id == rs.getInt("id_slots")){
            return true;
            }else{
            return false;
            }
        }
        return false;
    }  
        public Slot traerSlot(int id) throws Exception{
        String sql="select * from slots where id_slots=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            
            return from(rs);
        }
        else{
            return null;
        }
    }
        public List<Slot> listarSlots()throws Exception {
        List<Slot> r = new ArrayList<>();
        String sql = "select * from slots";
        try {
             PreparedStatement stm = Database.instance().prepareStatement(sql);
            ResultSet rs =  Database.instance().executeQuery(stm); 
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
            
        }
        return r;
    }
        // retorna lista completa de slots del medico
    public List<Slot> listarSlotsXmedico(int medico) throws Exception {
        List<Slot> r = new ArrayList<>();
        String sql = "select * from slots where medico=?";
        try {
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, medico);
            ResultSet rs = Database.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {

        }
        return r;
    }
        public Slot from (ResultSet rs) throws Exception{
            Medico usu =null;
        try {
            MedicoDao lol= new MedicoDao();
            Slot r= new Slot();
            r.setIdSlots(rs.getInt("id_slots"));
            r.setStartHour(rs.getInt("hora_inicio"));
            r.setEndHour(rs.getInt("hora_fin"));
            r.setDay(rs.getInt("dia"));
            usu = lol.traerMedico(rs.getInt("medico"));
            r.setMedico(usu);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    
}
