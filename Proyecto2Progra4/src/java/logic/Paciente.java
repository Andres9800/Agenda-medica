
package logic;


public class Paciente {
    int idPaciente;
    String nombre;
    char sexo;
    String contacto;
    String enfermedades;
    String alergias;
    String cirugias;
    Medico medico;

    public Paciente(int idPaciente, String nombre, char sexo, String contacto, String enfermedades, String alergias, String cirugias, Medico medico) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.sexo = sexo;
        this.contacto = contacto;
        this.enfermedades = enfermedades;
        this.alergias = alergias;
        this.cirugias = cirugias;
        this.medico = medico;
    }

    public Paciente(int idPaciente, String nombre, char sexo, String contacto, Medico medico) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.sexo = sexo;
        this.contacto = contacto;
        this.medico = medico;
    }

    public Paciente(int idPaciente, String nombre, char sexo, String contacto) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.sexo = sexo;
        this.contacto = contacto;
    }
    
    
    
    
    
    
    
    

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    

    public Paciente() {
        //this.usuario.setRol(2);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idPaciente;
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
        final Paciente other = (Paciente) obj;
        return this.idPaciente == other.idPaciente;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", sexo=" + sexo + ", contacto=" + contacto + ", enfermedades=" + enfermedades + ", alergias=" + alergias + ", cirugias=" + cirugias + ", medico=" + medico + '}';
    }




    
    

    
}
