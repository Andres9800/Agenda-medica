
package logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import data.Dao.SlotsDao;

public class Medico {
    String nombre;
    int idMedico;
    String especialidad;
    Double costo;
    String localidad;
    int duracion;
    String contrasena;
    Usuario usuario;
    List <Slot> schedule;



    public Medico(String nombre, int idMedico, String especialidad, Double costo, String localidad, int duracion, Usuario usuario) {
        this.nombre = nombre;
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.costo = costo;
        this.localidad = localidad;
        this.duracion = duracion;
        this.usuario = usuario;
        this.usuario.setRol(1);
        this.schedule = new ArrayList<>();
        this.contrasena = "";
    }
    

    public Medico(Usuario usuario) {
        this.nombre = " ";
        this.especialidad = " ";
        this.costo = 0.0;
        this.localidad = " ";
        this.duracion = 0;
        this.usuario = usuario;
        this.idMedico = 0;
        this.usuario.setRol(1);
        this.schedule = new ArrayList<>();
        this.contrasena = "";
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    

    public Medico() {
        //this.usuario.setRol(1);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Slot> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Slot> schedule) {
        this.schedule = schedule;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Double getCosto() {
        return costo;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Medico{" + "nombre=" + nombre + ", idMedico=" + idMedico + ", especialidad=" + especialidad + ", costo=" + costo + ", localidad=" + localidad + ", duracion=" + duracion + ", usuario=" + usuario + '}';
    }
    
    

    
    
//    List<Cita> dateAappointments(LocalDate date)throws Exception{
//        List<Cita> r = new ArrayList<>();
//        List<Slot> o = new ArrayList<>();
//        int day = date.getDayOfWeek().getValue();
//        LocalDateTime t;
//        LocalDateTime et;
//        SlotsDao lol= new SlotsDao();
//        o = lol.listarSlotsXmedico(this.getIdMedico());
//        // traer la lista de slots del medico de la base de datos(medico id)
//        for(Slot s:o){
//            if(s.getDay()==day){
//                t=date.atTime(s.getStartHour(), 0);
//                et = date.atTime(s.getEndHour(), 0);
//                while(t.isBefore(et)){
//                    r.add(new Cita(this,null,t));    
//                    t=t.plusMinutes(this.getDuracion());
//                }
//            }
//        }
//        return r;
//    }
//    
//    public List<List<Cita>> appointments(LocalDate start)throws Exception {
//        List<List<Cita>> r = new ArrayList<>();
//        LocalDate date = start.plusDays(0);
//        List<Cita> la;
//        for (int i = 0; i < 7; i++) {
//            la = dateAappointments(date);
//            if (!la.isEmpty()) {
//                r.add(la);
//            }
//            date = date.plusDays(1);
//        }
//        return r;
//    }
//    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idMedico;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        return this.idMedico == other.idMedico;
    }

    
    
    

    

    
}
