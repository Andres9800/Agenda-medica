
package logic;

import logic.Usuario;


public class Administrador {
    int id_admin;
    Usuario usuario;

    public Administrador(Usuario usuario,int id_admin) {
        this.usuario = usuario;
        this.id_admin=id_admin;
        this.usuario.setRol(3);
    }

    public Administrador(Usuario usuario) {
        this.usuario = usuario;
        this.usuario.setRol(3);
    }
    
    
    
    

    public Administrador() {
        //this.usuario.setRol(3);
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id_admin=" + id_admin + ", usuario=" + usuario + '}';
    }


    
}
