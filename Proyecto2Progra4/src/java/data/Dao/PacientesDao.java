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
import logic.Medico;
import logic.Paciente;
import logic.Usuario;


public class PacientesDao {
    
     public void create(String nombre,char sexo, String contacto, String enfermedades,String alegias,String cirugias,int idMedico ) throws Exception{
        String sql="insert into pacientes (nombre, sexo, contactoEmer, enfermedades, alergias, cirugias, medico) "+
                "values(?,?,?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, String.valueOf(sexo));
        stm.setString(3, contacto);
        stm.setString(4, enfermedades);
        stm.setString(5, alegias);
        stm.setString(6, cirugias);
        stm.setInt(7, idMedico);
        int count=Database.instance().executeUpdate(stm);
        if (count==0){
            throw new Exception("Paciente ya existe");
        }
    }
    
    
    public boolean consultarPaciente(int id) throws Exception{
       
       String sql="SELECT id_paciente FROM pacientes WHERE id_paciente = ?; ";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            if(id == rs.getInt("id_paciente")){
            return true;
            }else{
            return false;
            }
        }
        return false;
    }  
    public Paciente traerPaciente(int id) throws Exception{
        String sql="select * from pacientes where id_paciente=?";
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
                public boolean cambiarPaciente(char sexo, String contacto, String enfermedades,String alegias,String cirugias, int id) {
        try {
            String sql = "update pacientes set sexo = ?,contactoEmer = ?, enfermedades = ?, alergias = ?, cirugias = ? where id_paciente = ?";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setString(1, String.valueOf(sexo));
            stm.setString(2, contacto);
            stm.setString(3, enfermedades);
            stm.setString(4, alegias);
             stm.setString(5, cirugias);
            stm.setInt(6, id);
            Database.instance().executeUpdate(stm);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
        public List<Paciente> listarPacientesXMedico(int medico)throws Exception {
        List<Paciente> r = new ArrayList<>();
        String sql = "select * from pacientes  WHERE medico = ? ";
        try {
             PreparedStatement stm = Database.instance().prepareStatement(sql);
             stm.setInt(1, medico);
            ResultSet rs =  Database.instance().executeQuery(stm); 
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
            
        }
        return r;
    }
        
        public List<Paciente> listarPacientes()throws Exception {
        List<Paciente> r = new ArrayList<>();
        String sql = "select * from pacientes";
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
        
        
        // LISTAR PACIENTES POR MEDICO
        public Paciente from (ResultSet rs) throws Exception{
            Medico usu =null;
        try {
            MedicoDao lol= new MedicoDao();
            Paciente r= new Paciente();
            r.setIdPaciente(rs.getInt("id_paciente"));
            r.setNombre(rs.getString("nombre"));
            r.setSexo(rs.getString("sexo").charAt(0));
            r.setContacto(rs.getString("contactoEmer"));
            r.setEnfermedades(rs.getString("enfermedades"));
            r.setAlergias(rs.getString("alergias"));
            r.setCirugias(rs.getString("cirugias"));
            usu = lol.traerMedico(rs.getInt("medico"));
            r.setMedico(usu);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
}
