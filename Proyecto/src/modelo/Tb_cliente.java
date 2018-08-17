/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tiffy
 */
public class Tb_cliente {
     private String ci_cliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono1;
    private String telefono2;

    public Tb_cliente(String ci_cliente, String nombres, String apellidos, String direccion, String email,String tl1,String tl2) {
        this.ci_cliente = ci_cliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.telefono1 = tl1;
        this.telefono2 = tl2;
    }

    public Tb_cliente() {
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
     
    public String getCi_cliente() {
        return ci_cliente;
    }

    public void setCi_cliente(String ci_cliente) {
        this.ci_cliente = ci_cliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   

    public static Tb_cliente buscarCliente(String ci, Connection e) {
        try {
         //   connect();
            String consulta = "select c.ci_cliente, c.nombres,c.apellidos,c.direccion,c.email,t.telefono from db_flowbase.tb_cliente c join db_flowbase.tb_telefono t on c.ci_cliente= t.ci_cliente where c.ci_cliente=" + ci;
            Statement in = e.createStatement();
            ResultSet resultado = in.executeQuery(consulta);
            ArrayList<String> t= new ArrayList();
             Tb_cliente tb= new Tb_cliente();
            while(resultado.next()) {
                t.add(resultado.getString("t.telefono"));
                tb.setCi_cliente(resultado.getString("c.ci_cliente"));
                tb.setNombres(resultado.getString("c.nombres"));
                tb.setApellidos(resultado.getString("c.apellidos"));
                tb.setDireccion(resultado.getString("c.direccion"));
                tb.setEmail(resultado.getString("c.email"));               
            }
            if (t.size() > 0) {
                tb.setTelefono1(t.get(0));
                if (t.size() > 1) {
                    tb.setTelefono2(t.get(1));
                }
            }          
            return tb;
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
     //   cerrarConexion();
        return null;
    }
    
    public static Tb_cliente buscarClientePorNombres(String nom,String ape, Connection e) {
        try {
            String consulta = "select c.ci_cliente, c.nombres,c.apellidos,c.direccion,c.email,t.telefono "
                    + "from db_flowbase.tb_cliente c join db_flowbase.tb_telefono t on c.ci_cliente= t.ci_cliente "
                    + "where c.nombres=? and c.apellidos=?" ;        
            PreparedStatement ingreso = e.prepareStatement(consulta);
            ingreso.setString(1, nom.toLowerCase());
            ingreso.setString(2, ape.toLowerCase());
            ResultSet resultado = ingreso.executeQuery();
            ArrayList<String> t = new ArrayList();
            Tb_cliente tb = new Tb_cliente();
            while (resultado.next()) {
                t.add(resultado.getString("t.telefono"));
                tb.setCi_cliente(resultado.getString("c.ci_cliente"));
                tb.setNombres(resultado.getString("c.nombres"));
                tb.setApellidos(resultado.getString("c.apellidos"));
                tb.setDireccion(resultado.getString("c.direccion"));
                tb.setEmail(resultado.getString("c.email"));

            }
            if (t.size() > 0) {
                tb.setTelefono1(t.get(0));
                if (t.size() > 1) {
                    tb.setTelefono2(t.get(1));
                }
            }
            return tb;
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
        //   cerrarConexion();
        return null;
    }
    
    public static void ingresarDatosCliente(String ci,String nombres,String apellidos, String direccion, String email,Connection c){
    try {
            String consulta= "insert into db_flowbase.tb_cliente values (?,?,?,?,?)";
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, ci);
             ingreso.setString(2, nombres.toLowerCase());
             ingreso.setString(3, apellidos.toLowerCase());
             ingreso.setString(4, direccion.toLowerCase());
             ingreso.setString(5, email.toLowerCase());
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("ingreso exitoso cliente...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
      public static void actualizarDatosCliente(String ci,String nombres,String apellidos, String direccion, String email,Connection c){
    try {
            String consulta= "update db_flowbase.tb_cliente set nombres=?,apellidos=?,direccion=?,email=? where ci_cliente="+ci;
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, nombres.toLowerCase());
             ingreso.setString(2, apellidos.toLowerCase());
             ingreso.setString(3, direccion.toLowerCase());
             ingreso.setString(4, email.toLowerCase());
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("Actualizacion exitosa cliente...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
      public static void ingresarTelefonosCliente(String telefono, String ci, Connection c) {
        try {
            String consulta = "insert into db_flowbase.tb_telefono(telefono,ci_cliente) values (?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setString(1, telefono);
            ingreso.setString(2, ci);
            int j = ingreso.executeUpdate();
            if (j > 0) { //BORRAR LUEGO
                System.out.println("ingreso exitoso telefono...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION Telefono: " + ex.getMessage());
        }
    }

    public static void actualizarTelefonosCliente(String telefonoNuevo, String telefonoAnterior, String ci, Connection c) {
        try {
            String consulta = "update db_flowbase.tb_telefono set telefono=? where ci_cliente=" + ci + " and telefono=" + telefonoAnterior;
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setString(1, telefonoNuevo);
            int j = ingreso.executeUpdate();
            if (j > 0) { //BORRAR LUEGO
                System.out.println("Actualizacion telefono exitosa...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }


    @Override
    public String toString() {
        return "ci_cliente: " + ci_cliente + "Nombres: " + nombres + "Apellidos: " + apellidos + "Direccion: " + direccion + "email: " + email + " tlf1: " + telefono1 +" tl2 "+ telefono2 ;
    }
}
