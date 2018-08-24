/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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
        return "Destinatario{" + "cod_destinatario=" + cod_destinatario + ", nombres=" + nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", telefono=" + telefono + ", ref_dir=" + ref_dir + '}';
    }
    
    public static void ingresarDestinatario(int cod, String nombres, String apellidos,
            String direccion, String telefono, String ref_dir ,Connection c){
        try {
            String consulta = "insert into db_flowbase.tb_pedido values (?,?,?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setInt(1, cod);
            ingreso.setString(2, nombres);
            ingreso.setString(3, apellidos);
            ingreso.setString(4, direccion);
            ingreso.setString(5, telefono);
            ingreso.setString(6, ref_dir);
            int p = ingreso.executeUpdate();
            if(p > 0){ 
                System.out.println("ingreso exitoso de destinatario...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION (destinarario): " + ex.getMessage());
        }
    }
     
}
