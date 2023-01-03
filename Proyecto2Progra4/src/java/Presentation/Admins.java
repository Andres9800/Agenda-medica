package Presentation;

import data.Dao.AdministradorDao;
import data.Dao.MedicoDao;
import data.Dao.PacientesDao;
import data.Dao.SlotsDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Dao.UsuarioDao;
import java.util.List;
import logic.Usuario;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import logic.Medico;
import logic.Paciente;
import logic.Slot;

@Path("/admins")
@PermitAll
public class Admins {

    @Context
    HttpServletRequest request;
    
    private Usuario us = null;
    private Medico med = null;
    UsuarioDao miDaoUsuario = new UsuarioDao();
    AdministradorDao miDaoAdmin = new AdministradorDao();
    MedicoDao miDaoMedico = new MedicoDao();
    SlotsDao miDaoSlot = new SlotsDao();
        PacientesDao miDaoPaceintes = new PacientesDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarMedicos() {
        try {
            return miDaoUsuario.listarUsuariosMedicos();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)  
    public void update(int cedula) {  
        try {
            miDaoUsuario.cambiarStatus("activo", cedula);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
    @GET
    @Path("{idMedico}")
    @Produces({MediaType.APPLICATION_JSON})
    public Medico retornaMedico(@PathParam("idMedico") int cedula) {
        try {
            med = miDaoMedico.traerMedicoXusuario(cedula);
            return med;
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    

    

    
    
    
    
    
     }
