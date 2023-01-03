package Presentation;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class RestConfiguration extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        classes.add(Usuarios.class);
        classes.add(Admins.class);
        classes.add(Pacientes.class);
        classes.add(Varios.class);
        classes.add(CitasPacientes.class);
        classes.add(CitasTerminar.class);
        classes.add(Agenda.class);
        classes.add(AgendaCitas.class);
        classes.add(AgendaCitas2.class);
        classes.add(MultiPartFeature.class);
        return classes;
    }    
}
