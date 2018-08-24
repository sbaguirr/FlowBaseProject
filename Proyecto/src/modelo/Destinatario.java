/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static controlador.VentanaDialogo.DestinatarioGuardadoExitosamente;
import static controlador.VentanaDialogo.DestinatarioGuardadoFallido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ROSA
 */
public class Destinatario {
    private int cod_destinatario;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String ref_dir;

    public int getCod_destinatario() {
        return cod_destinatario;
    }

    public void setCod_destinatario(int cod_destinatario) {
        this.cod_destinatario = cod_destinatario;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRef_dir() {
        return ref_dir;
    }

    public void setRef_dir(String ref_dir) {
        this.ref_dir = ref_dir;
    }

    public Destinatario(int cod_destinatario, String nombres, String apellidos, String direccion, String telefono, String ref_dir) {
        this.cod_destinatario = cod_destinatario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ref_dir = ref_dir;
    }
    
    public Destinatario(){}

    @Override
    public String toString() {
        return cod_destinatario + nombres + apellidos +direccion + telefono + ref_dir;
    }
    
    public static void ingresarDestinatario(int cod_destinatario, String nombres, 
            String apellidos, String direccion, String telefono, String ref_dir ,Connection c){
        try {
            String consulta = "insert into db_flowbase.tb_destinatario values (?,?,?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setInt(1, cod_destinatario);
            ingreso.setString(2, nombres);
            ingreso.setString(3, apellidos);
            ingreso.setString(4, direccion);
            ingreso.setString(5, telefono);
            ingreso.setString(6, ref_dir);
            int p = ingreso.executeUpdate();
            if(p > 0){ 
                System.out.println("ingreso exitoso de destinatario...");
                DestinatarioGuardadoExitosamente();
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION (destinarario): " + ex.getMessage());
            DestinatarioGuardadoFallido();
        }
    }
     
    public static int buscarLastDest(Connection e) {
        int destinat = 1;
        try {
            String consulta = "SELECT cod_destinatario " +
                                "FROM db_flowbase.tb_destinatario " +
                                "order by cod_destinatario desc " +
                                "limit 1;";
            Statement in = e.createStatement();
            ResultSet resultado = in.executeQuery(consulta);
            if(resultado.next()) {
                destinat = destinat + resultado.getInt("cod_destinatario");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION   : " + ex.getMessage());
        }
        return destinat;
    }
}
