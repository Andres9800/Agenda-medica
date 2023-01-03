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
import java.io.IOException;
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
import javax.ws.rs.core.Response;
import logic.Medico;
import logic.Paciente;
import logic.Slot;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/agenda")
@PermitAll
public class Agenda {

    String location2 = "C:/AAA/pdf/";

    @Context
    HttpServletRequest request;

    private Usuario us = null;
    UsuarioDao miDaoUsuario = new UsuarioDao();
    AdministradorDao miDaoAdmin = new AdministradorDao();
    MedicoDao miDaoMedico = new MedicoDao();
    SlotsDao miDaoSlot = new SlotsDao();
    PacientesDao miDaoPaceintes = new PacientesDao();




    
    @GET
    @Path("{idMediquito}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Slot> traerListaSlostM(@PathParam("idMediquito") int paciEn) {
        try {
            Medico med = miDaoMedico.traerMedicoXusuario(paciEn);
            return miDaoSlot.listarSlotsXmedico(med.getIdMedico());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Path("{id}/pdf")
    @Produces("application/pdf")
    public Response getPDF(@PathParam("id") int id) throws IOException {
        File file = new File(location2+id);
        Response.ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }
        

}
