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
import logic.Medico;
import logic.Paciente;
import logic.Slot;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/pacientes")
@PermitAll
public class Pacientes {

    String location = "C:/AAA/images/";

    @Context
    HttpServletRequest request;

    private Usuario us = null;
    UsuarioDao miDaoUsuario = new UsuarioDao();
    AdministradorDao miDaoAdmin = new AdministradorDao();
    MedicoDao miDaoMedico = new MedicoDao();
    SlotsDao miDaoSlot = new SlotsDao();
    PacientesDao miDaoPaceintes = new PacientesDao();



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPacientes(Paciente p) {
        try {
            Medico med = miDaoMedico.traerMedicoXusuario(p.getIdPaciente());
            miDaoPaceintes.create(p.getNombre(), p.getSexo(), p.getContacto(), p.getEnfermedades(), p.getAlergias(), p.getCirugias(), med.getIdMedico());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{cedula}/imagen")
    public void addImage(@PathParam("cedula") String cedula, @FormDataParam("imagen") InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(location + cedula));
            //OutputStream out = new FileOutputStream(new File(location + cedula + ".jpg"));
            in.transferTo(out);
            out.close();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    


    @GET
    @Path("{idMedic}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> listarPacientes(@PathParam("idMedic") int cedula) {
        try {
            Medico med = miDaoMedico.traerMedicoXusuario(cedula);
            return miDaoPaceintes.listarPacientesXMedico(med.getIdMedico());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    public void updatePacie(Paciente p) {  
        try {
            miDaoPaceintes.cambiarPaciente(p.getSexo(),p.getContacto(),p.getEnfermedades(),p.getAlergias(),p.getCirugias(),p.getIdPaciente());
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateMedico(Medico p) {
//        try {
//            miDaoMedico.cambiarMedico(p.getNombre(), p.getEspecialidad(), p.getCosto(), p.getLocalidad(), p.getDuracion(), p.getIdMedico());
//        } catch (Exception ex) {
//            throw new NotFoundException();
//        }
//    }
}
