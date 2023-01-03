
package logic;


public class Lugar {
    int id;
    String lugar;

    public Lugar(int id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }

    public Lugar(String lugar) {
        this.lugar = lugar;
    }

    public Lugar() {
        this.lugar = "";
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "Lugar{" + "id=" + id + ", lugar=" + lugar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Lugar other = (Lugar) obj;
        return this.id == other.id;
    }


    
    
}
