package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Cita {
    int id;
    Date day;
    Medico medico;
    Paciente paciente;
    String estado;
    String signos;
    String diagnostico;
    String prescripciones;
    String motivo;

    public Cita(int id, Date day, Medico medico, Paciente paciente, String estado, String signos, String diagnostico, String prescripciones, String motivo) {
        this.id = id;
        this.day = day;
        this.medico = medico;
        this.paciente = paciente;
        this.estado = "pendiente";
        this.signos = signos;
        this.diagnostico = diagnostico;
        this.prescripciones = prescripciones;
        this.motivo = motivo;
    }



    public Cita( Medico medico, Paciente paciente, Date day) { //(Doctor doctor, Paciente paciente, LocalDateTime day) {
        this.day = day;
        this.medico = medico;
        this.paciente = paciente;
        this.estado = "pendiente";
        this.signos = "NA";
        this.diagnostico = "NA";
        this.prescripciones = "NA";
        this.motivo = "NA";
        
    }

    public Cita() {
        this.paciente=new Paciente();
        this.medico=new Medico();
        this.estado = "";
        //this.day = LocalDateTime.now();
        this.signos = "NA";
        this.diagnostico = "NA";
        this.prescripciones = "NA";
        this.motivo = "NA";
        this.day = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }






    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getSignos() {
        return signos;
    }

    public void setSignos(String signos) {
        this.signos = signos;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(String prescripciones) {
        this.prescripciones = prescripciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

   
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cita{" + "id=" + id + ", day=" + day + ", medico=" + medico + ", paciente=" + paciente + ", estado=" + estado + ", signos=" + signos + ", diagnostico=" + diagnostico + ", prescripciones=" + prescripciones + ", motivo=" + motivo + '\n';
    }

    public String transformarFormatoDato() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String format = formatter.format(day);
        return format;
    }
    public void transformarFormatoADate(String fe) throws ParseException{
        this.day = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(fe);
    }






    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Cita other = (Cita) obj;
        return this.id == other.id;
    }
    
    

    
}
