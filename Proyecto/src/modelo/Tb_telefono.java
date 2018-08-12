/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Tiffy
 */
public class Tb_telefono {
        private int id_tlf;
    private String telefono;
    private String cliente;

    public Tb_telefono(int id_tlf, String telefono, String cliente) {
        this.id_tlf = id_tlf;
        this.telefono = telefono;
        this.cliente = cliente;
    }

    public Tb_telefono(String telefono, String cliente) {
        this.telefono = telefono;
        this.cliente = cliente;
    }

    public int getId_tlf() {
        return id_tlf;
    }

    public void setId_tlf(int id_tlf) {
        this.id_tlf = id_tlf;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Telefono " + telefono + "Cliente: " + cliente;
    }
public static void ingresarTelefonosCliente(String telefono,String ci,Connection c){
    try {
            String consulta= "insert into db_flowbase.tb_telefono(telefono,ci_cliente) values (?,?)";
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, telefono);
             ingreso.setString(2, ci);
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("ingreso exitoso telefono...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
 
public static void actualizarTelefonosCliente(String telefonoNuevo,String telefonoAnterior,String ci,Connection c){
    try {
            String consulta= "update db_flowbase.tb_telefono set telefono=? where ci_cliente="+ci+" and telefono="+telefonoAnterior;
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, telefonoNuevo);
            // ingreso.setString(2,ci);
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("Actualizacion telefono exitosa...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
}
