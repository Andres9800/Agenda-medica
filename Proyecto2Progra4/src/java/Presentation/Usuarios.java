package Presentation;

import data.Dao.AdministradorDao;
import data.Dao.MedicoDao;
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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import logic.Medico;
import logic.Slot;

@Path("/usuarios")
@PermitAll
public class Usuarios {

    @Context
    HttpServletRequest request;

    private Usuario us = null;
    UsuarioDao miDaoUsuario = new UsuarioDao();
    AdministradorDao miDaoAdmin = new AdministradorDao();
    MedicoDao miDaoMedico = new MedicoDao();
    SlotsDao miDaoSlot = new SlotsDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMedico(Medico p) {
        try {
            miDaoMedico.create(p.getNombre(), p.getEspecialidad(), p.getCosto(), p.getLocalidad(), p.getDuracion(), p.getIdMedico(), p.getContrasena());
            Medico temporal = miDaoMedico.traerMedicoXusuario(p.getIdMedico());

            List<Slot> miLista = p.getSchedule();
            for (int i = 0; i < miLista.size(); i++) {
                miDaoSlot.create(miLista.get(i).getStartHour(), miLista.get(i).getEndHour(), miLista.get(i).getDay(), temporal.getIdMedico());
            }

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @POST
    @Path("{addAdmin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAdmin(Usuario p) {
        try {
            miDaoUsuario.create(p.getId(), p.getContrasena(), 3);
            miDaoAdmin.create(p.getId());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("{idpara}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario read(@PathParam("idpara") int cedula) {
        try {
            request.getSession(true).setAttribute("doctor", us);
            us = miDaoUsuario.traerUsuarioId(cedula);
            return us;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)  
//    public void add(Usuario p) {
//        try {
//            us = Service.getInstance().buscarUsuario(p);
//            if(us != null){
//                throw new Exception("No es posible registrarse");
//            }
//            Service.getInstance().agregarUsuario(p);
//        } catch (Exception ex) {
//            throw new NotAcceptableException();
//        }
//    }
//    @POST
//    @Path("{login}")
//   
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON})
//    public Usuario login(Usuario p) {
//        try {
//            us = miDaoUsuario.traerUsuarioId(p.getId());
//            if(us == null){
//                throw new Exception("Nombre o contrasenia invalidados");
//            }
//            request.getSession(true).setAttribute("user", us);
//            return us;
//        } catch (Exception ex) {
//            throw new NotAcceptableException();
//        }
//    }
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Usuario getCurrent() {
//        try {
//            us=(Usuario)request.getSession(true).getAttribute("user");
//            if (us == null) {
//                throw new Exception();
//            }
//            return us;
//        } catch (Exception ex) {
//            throw new NotAcceptableException();
//        }
//    }
    @DELETE
    public void logout() {
        HttpSession session = request.getSession(true);
        session.removeAttribute("doctor");
        session.invalidate();
        us = null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMedico(Medico p) {
        try {
            miDaoMedico.cambiarMedico(p.getNombre(), p.getEspecialidad(), p.getCosto(), p.getLocalidad(), p.getDuracion(), p.getIdMedico());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
