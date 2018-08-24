/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiffy
 */
public class Tb_inventario {

    private String codigo;
    private String nombre;
    private int stock;
    private int cantidadVendida;

    public Tb_inventario(String codigo, String nombre,int stock, int cantidadVendida) {
        this.codigo = codigo;
        this.nombre=nombre;
        this.stock = stock;
        this.cantidadVendida = cantidadVendida;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    
    public static List<Tb_inventario> callInventario(String fecha, String ci, Connection c){
     List<Tb_inventario> t= new ArrayList<>();
        try {
            String consulta = "{call inventario(?,?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1,fecha);
            sp.setString(2, ci);
            sp.execute();
            final ResultSet resultado = sp.getResultSet();
            while (resultado.next()) {
                t.add(
                        new Tb_inventario(
                                resultado.getString("i.cod_articulo"),
                                resultado.getString("a.nombre"),
                                resultado.getInt("ea.stock"),
                                resultado.getInt("cantidadVendida")
                                ));
            }
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callInventario: " + ex.getMessage());
        }
        return t;
    }
    
    @Override
    public String toString() {
        return "Tb_inventario{" + "codigo=" + codigo + ", stock=" + stock + ", cantidadVendida=" + cantidadVendida + '}';
    }
    
}
