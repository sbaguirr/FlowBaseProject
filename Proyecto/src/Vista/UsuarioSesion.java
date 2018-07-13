package Vista;



import java.util.LinkedList;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROSA
 */
public class UsuarioSesion {
    private String user;
    private String contra;

    public UsuarioSesion(String user, String contra){
        this.user=user;
        this.contra=contra;
    }
    
    public static LinkedList<UsuarioSesion> Usuarios(){
        LinkedList<UsuarioSesion> listaUsuarios = new LinkedList<>();
        listaUsuarios.add(new UsuarioSesion("Rog", "rog"));
        listaUsuarios.add(new UsuarioSesion("Rosa", "1234"));
        listaUsuarios.add(new UsuarioSesion("Stef", "stef"));
        return listaUsuarios;
    }
    
    public String getUser() {
        return user;
    }

    public String getContra() {
        return contra;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final UsuarioSesion other = (UsuarioSesion) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.contra, other.contra)) {
            return false;
        }
        return true;
    }
    
    
    
    public boolean validarUsuario(String user, String contra){
        for (UsuarioSesion u : Usuarios()){
            if(!(u.equals(user) && u.equals(contra))){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Usuario{" + "user=" + user + ", contra=" + contra + '}';
    }
    
}
