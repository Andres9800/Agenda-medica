
package logic;

public class Usuario {
    int id;
    String contrasena;
    int rol;
    String estado;

    public Usuario(int id, String contrasena, int rol) {
        this.id = id;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Usuario(int id, String contrasena, int rol, String estado) {
        this.id = id;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = estado;
    }
    
    
    
    

    public Usuario(int id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    public Usuario() {
        this.id = 0;
        this.contrasena = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", contrasena=" + contrasena + ", rol=" + rol + ", estado=" + estado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Usuario other = (Usuario) obj;
        return this.id == other.id;
    }


    
    
    
    
    
    
}
