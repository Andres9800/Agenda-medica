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
import logic.Usuario;

public class MedicoDao {
    
     public void create(String nombre,String especialidad, double costo, String localidad,int duracion,int idUsuario , String contrasena) throws Exception{
        String sql="insert into medicos (nombre, especialidad, costo, localidad, duracion, usuario) "+
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, especialidad);
        stm.setDouble(3, costo);
        stm.setString(4, localidad);
        stm.setInt(5, duracion);
        stm.setInt(6, idUsuario);
        UsuarioDao miDaoUsu = new UsuarioDao();
        
        miDaoUsu.create(idUsuario, contrasena, 1);
        
        int count=Database.instance().executeUpdate(stm);
        if (count==0){
            throw new Exception("Medico ya existe");
        }
    }
         public void createInico(int idUsuario ) throws Exception{
        String sql="insert into medicos (usuario) "+
                "values(?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, idUsuario);
        int count=Database.instance().executeUpdate(stm);
        if (count==0){
            throw new Exception("Medico ya existe");
        }
    } 
     
        public boolean cambiarMedico(String nombre,String especialidad,double costo,String localidad,int duracion, int id) {
        try {
            String sql = "update medicos set nombre = ?,especialidad = ?, costo = ?, localidad = ?, duracion = ? where id_medico = ?";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, especialidad);
            stm.setDouble(3, costo);
            stm.setString(4, localidad);
             stm.setInt(5, duracion);
            stm.setInt(6, id);
            Database.instance().executeUpdate(stm);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    public boolean consultarMedico(int id) throws Exception{
       
       String sql="SELECT id_medico FROM medicos WHERE id_medico = ?; ";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            if(id == rs.getInt("id_medico")){
            return true;
            }else{
            return false;
            }
        }
        return false;
    }
        public int retornaIdUsuarioXidMedico(int id) throws Exception{
       int u = 0;
       String sql="SELECT usuario FROM medicos WHERE id_medico = ?; ";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
             u = rs.getInt("usuario");
             return u;
        }
        throw new Exception("Medico No existe");
        }
     
    
    
    public Medico traerMedico(int id) throws Exception{
        String sql = "select * from medicos where id_medico=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {

            return from(rs);
        } else {
            return null;
        }
    }
        public Medico traerMedicoXusuario(int id) throws Exception{
        String sql="select * from medicos where usuario=?";
        try {
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            return null;
        }
        }catch (SQLException ex) {
            
        }
        return null;
    }
        
        public List<Medico> listarMedicos()throws Exception {
        List<Medico> r = new ArrayList<>();
        String sql = "select * from medicos";
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
        public List<Medico> listarMedicosXlugarYespec(String lugar , String especialidad) throws Exception {
        List<Medico> r = new ArrayList<>();
        String sql = "select * from medicos where localidad= ? and especialidad = ?";
        try {
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setString(1, lugar);
            stm.setString(2, especialidad);
            ResultSet rs = Database.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {

        }
        return r;
    }
        
        
        public Medico from (ResultSet rs) throws Exception{
            Usuario usu =null;
        try {
            UsuarioDao lol= new UsuarioDao();
            Medico r= new Medico();
            r.setIdMedico(rs.getInt("id_medico"));
            r.setNombre(rs.getString("nombre"));
            r.setEspecialidad(rs.getString("especialidad"));
            r.setCosto(rs.getDouble("costo"));
            r.setLocalidad(rs.getString("localidad"));
            r.setDuracion(rs.getInt("duracion"));
            usu = lol.traerUsuarioId(rs.getInt("usuario"));
            r.setUsuario(usu);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    
    
}
