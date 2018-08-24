/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiffy
 */
public class Tb_reporteVentaVendedor {

    private String id;
    private String nombres;
    private String apellidos;
    private float venta;

    public Tb_reporteVentaVendedor(String id, String nombres, String apellidos, float venta) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.venta = venta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public float getVenta() {
        return venta;
    }

    public void setVenta(float venta) {
        this.venta = venta;
    }

    public static List<Tb_reporteVentaVendedor> ejecutarVista1(Connection c) {
        List<Tb_reporteVentaVendedor> rp = new ArrayList<>();
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
                    "SELECT * FROM db_flowbase.montos_de_venta_por_vendedor");
            while (resultado.next()) {
                rp.add(
                        new Tb_reporteVentaVendedor(
                                resultado.getString("Id"),
                                resultado.getString("Nombre"),
                                resultado.getString("Apellido"),
                                resultado.getFloat("Venta")
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION in llenar ejecutar vista1 : " + ex.getMessage());
        }
        return rp;
    }
    
    public static List<Tb_reporteVentaVendedor> ejecutarVista2(Connection c) {
        List<Tb_reporteVentaVendedor> rp = new ArrayList<>();
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
                    "SELECT * FROM db_flowbase.ventas_actuales_por_vendedor");
            while (resultado.next()) {
                rp.add(
                        new Tb_reporteVentaVendedor(
                                resultado.getString("Id"),
                                resultado.getString("Nombre"),
                                resultado.getString("Apellido"),
                                resultado.getFloat("Venta")
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION in llenar Tb_ejecutarVista2: " + ex.getMessage());
        }
        return rp;
    }

    @Override
    public String toString() {
        return "Tb_reporteVentaVendedor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos ;
    }
    
    
}
