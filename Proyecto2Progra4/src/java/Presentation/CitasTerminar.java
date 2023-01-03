package Presentation;

import data.Dao.AdministradorDao;
import data.Dao.CitasDao;
import data.Dao.MedicoDao;
import data.Dao.PacientesDao;
import data.Dao.SlotsDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Dao.UsuarioDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import logic.Cita;
import logic.Medico;
import logic.Paciente;
import logic.Slot;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/citasTerminar")
@PermitAll
public class CitasTerminar {

    //String location = "C:/AAA/images/";

    @Context
    HttpServletRequest request;

    private Usuario us = null;
    UsuarioDao miDaoUsuario = new UsuarioDao();
    AdministradorDao miDaoAdmin = new AdministradorDao();
    MedicoDao miDaoMedico = new MedicoDao();
    SlotsDao miDaoSlot = new SlotsDao();
    PacientesDao miDaoPaceintes = new PacientesDao();
    CitasDao miDaoCitas = new CitasDao();




    
    @GET
    @Path("{idCita}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cita getPacientel(@PathParam("idCita") int idCita) {
        try {
            return miDaoCitas.traerCita(idCita);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    public void updateCita(Cita p) {  
        try {
            miDaoCitas.actualizarCita(p.getEstado(),p.getSignos(),p.getDiagnostico(),p.getPrescripciones(),p.getId());
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    // post de agregar cita
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCita1(Cita p) {
        try {
            Medico med = miDaoMedico.traerMedicoXusuario(p.getId());
            int pac = Integer.parseInt(p.getEstado());
            miDaoCitas.create(p.getDay(), med.getIdMedico(),pac, p.getMotivo());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    
    
    
    
    

}
