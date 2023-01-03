
package logic;


public class Especialidad {
    int id;
    String especialidad;

    public Especialidad(int id, String especialidad) {
        this.id = id;
        this.especialidad = especialidad;
    }

    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Especialidad() {
        this.especialidad="";
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        final Especialidad other = (Especialidad) obj;
        return this.id == other.id;
    }


    @Override
    public String toString() {
        return "Especialidad{" + "id=" + id + ", especialidad=" + especialidad + '}';
    }
    
    
}
