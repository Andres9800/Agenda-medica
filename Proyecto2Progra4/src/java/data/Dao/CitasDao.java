//#Proyecto #2 Sistema de Expediente Electronico
//#-Wendy Largaespada Aragon
//#-Andres Rojas Rojas
//#-Andres Cordero Gutierrez
package data.Dao;

import data.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logic.Cita;
import logic.Medico;
import logic.Paciente;

public class CitasDao {



    public void create(Date fechaL, int medicoo, int pacientee , String motivo) throws Exception {
        
        String sql = "insert into citas (fecha, medico, paciente, motivo) "
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaL);
        java.util.Date da = cal.getTime();
        Timestamp timestamp = new Timestamp(da.getTime());
        stm.setTimestamp(1, timestamp);
        stm.setInt(2, medicoo);
        stm.setInt(3, pacientee);
        stm.setString(4, motivo);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cita ya existe");
        }
    }

    public boolean actualizarCita(String estado,String signos, String diagnostico,String prescripciones, int id) {
        try {
            String sql = "update citas set estado = ?, signos = ?, diagnostico = ?, prescripciones = ? where id_cita = ?";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setString(1, estado);
            stm.setString(2, signos);
            stm.setString(3, diagnostico);
            stm.setString(4, prescripciones);
            stm.setInt(5, id);
            Database.instance().executeUpdate(stm);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean consultarCita(int id) throws Exception { // consulta cita por su id

        String sql = "SELECT id_cita FROM citas WHERE id_cita = ?; ";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            if (id == rs.getInt("id_cita")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Cita traerCita(int id) throws Exception {
        String sql = "select * from citas where id_cita=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            return null;
        }
    }
        public Cita traerCitaXMotivo(String motivo) throws Exception {
        String sql = "select * from citas where motivo=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, motivo);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            return null;
        }
    }
    

    public List<Cita> listarCitas() throws Exception {
        List<Cita> r = new ArrayList<>();
        String sql = "select * from citas";
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
    // retorna lista completa de citas del medico

    public List<Cita> listarCitasXmedico(int medico)throws Exception{
        List<Cita> r = new ArrayList<>();
        String sql = "select * from citas where medico = ?";
        try {
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, medico);
            ResultSet rs = Database.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
                throw new Exception("Cita ya existe");
        }
        return r;
    }
// retorna lista completa de citas del Paciente

    public List<Cita> listarCitasXPaciente(int paci) throws Exception {
        List<Cita> r = new ArrayList<>();
        String sql = "select * from citas where paciente = ?";
        try {
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, paci);
            ResultSet rs = Database.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {

        }
        return r;
    }

    public Cita from(ResultSet rs)throws Exception {
        try {
            Cita r = new Cita();
            r.setId(rs.getInt("id_cita"));
            r.setEstado(rs.getString("estado"));
            
            r.setSignos(rs.getString("signos"));
            r.setDiagnostico(rs.getString("diagnostico"));
            r.setPrescripciones(rs.getString("prescripciones"));
            r.setMotivo(rs.getString("motivo"));
            
            java.util.Date utilDate = rs.getTimestamp("fecha");
            
            utilDate.setHours(utilDate.getHours()-6);
            r.setDay(utilDate);
            
            MedicoDao lol = new MedicoDao();
            Medico usu = lol.traerMedico(rs.getInt("medico"));
            if(usu==null){
                throw new Exception("Cita ya existe");
            }
            r.setMedico(usu);
            PacientesDao lol2 = new PacientesDao();
            Paciente paci = lol2.traerPaciente(rs.getInt("paciente"));
            if(paci==null){
                throw new Exception("Cita ya existe");
            }
            r.setPaciente(paci);
            return r;
        } catch (SQLException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
    
    


}
