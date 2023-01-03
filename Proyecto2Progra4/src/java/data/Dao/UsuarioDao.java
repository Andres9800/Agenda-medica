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
import data.Database;

import logic.Usuario;

public class UsuarioDao {
    
    public void create(int id, String clave, int rol ) throws Exception{
        String sql="insert into usuarios (id_usuario, contrasena, rol) "+
                "values(?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        stm.setString(2, clave);
        stm.setInt(3, rol);
      
        int count=Database.instance().executeUpdate(stm);
        if (count==0){
            throw new Exception("Usuario ya existe");
        }
    }
    public void cambiarStatus(String estado, int id) throws Exception{
        try {
            String sql = "update usuarios set estado = ? where id_usuario = ?";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setString(1, estado);
            stm.setInt(2, id);
            Database.instance().executeUpdate(stm);
        } catch (SQLException ex) {
            throw new Exception("Usuario no existe");

        }
    }
    
    
    
    public boolean consultarUsuario(int id) throws Exception{
       
       String sql="SELECT id_usuario FROM usuarios WHERE id_usuario = ?; ";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            if(id==rs.getInt("id_usuario")){
            return true;
            }else{
            return false;
            }
        }
        return false;
    }
    
        public Usuario traerUsuario(int id,String cla) throws Exception{
        String sql="select * from usuarios where id_usuario=? AND contrasena=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        stm.setString(2, cla);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            
            return from(rs);
        }
        else{
            return null;
        }
    }
                public Usuario traerUsuarioId(int id) throws Exception{
        String sql="select * from usuarios where id_usuario=?";
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
        public List<Usuario> listarUsuarios() {
        List<Usuario> r = new ArrayList<>();
        String sql = "select * from usuarios";
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
    public List<Usuario> listarUsuariosMedicos() {
        List<Usuario> r = new ArrayList<>();
        String sql = "select * from usuarios where rol = 1";
        try {
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            ResultSet rs = Database.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
        
        
        
        public Usuario from (ResultSet rs){
        try {
            Usuario r= new Usuario(); //creamos el usuario
            r.setRol(rs.getInt("rol"));
            r.setId(rs.getInt("id_usuario"));
            r.setContrasena(rs.getString("contrasena"));
            r.setEstado(rs.getString("estado"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
        
    

    
    

    
}
