//#Proyecto #2 Sistema de Citas
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
import logic.Administrador;
import logic.Usuario;

public class AdministradorDao {

    public void create(int idUsuario) throws Exception {
        String sql = "insert into administrador (usuario) "
                + "values(?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, idUsuario);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Admin ya existe");
        }
    }

    public boolean consultarAdmin(int id) throws Exception {

        String sql = "SELECT id_administrador FROM administrador WHERE id_administrador = ?; ";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            if (id == rs.getInt("id_administrador")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Administrador traerAdmin(int id) throws Exception {
        String sql = "select * from administrador where id_administrador=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {

            return from(rs);
        } else {
            return null;
        }
    }

    public List<Administrador> listarAdministradores() throws Exception {
        List<Administrador> r = new ArrayList<>();
        String sql = "select * from administrador";
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

    public Administrador from(ResultSet rs) throws Exception {
        Usuario usu = null;
        try {
            UsuarioDao lol = new UsuarioDao();
            Administrador r = new Administrador();
            r.setId_admin(rs.getInt("id_administrador"));
            usu = lol.traerUsuarioId(rs.getInt("usuario"));
            r.setUsuario(usu);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

}
