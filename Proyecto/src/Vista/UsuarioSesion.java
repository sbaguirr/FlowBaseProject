package Vista;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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
        listaUsuarios.add(new UsuarioSesion("admin", "admin"));
        listaUsuarios.add(new UsuarioSesion("rog", "rog"));
        listaUsuarios.add(new UsuarioSesion("rosa", "1234"));
        listaUsuarios.add(new UsuarioSesion("stef", "stef"));
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
    
    public static String validarUser(String user, String pass, Connection c){
        String ci=null;
        try {
            String consulta = "{call login(?,?,?)}"; 
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1, user); //ingresar valor
            sp.setString(2, pass);
            sp.registerOutParameter(3, Types.VARCHAR); //configura para que retorne
            sp.execute();
            ci= sp.getString(3);
            sp.close();
            //return ci;
        } catch (SQLException ex) {
            System.out.println("EXCEPCION user: " + ex.getMessage());
        }      
        return ci;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "user=" + user + ", contra=" + contra + '}';
    }
    
}
