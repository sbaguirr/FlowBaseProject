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
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ROSA
 */
public class Articulo {
    private String cod_articulo;
    private String nombre;
    private String descripcion;
    private float costo;
    private String color;

    public Articulo(String cod_articulo, String nombre, String descripcion, float costo, String color) {
        this.cod_articulo = cod_articulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.color = color;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public static void llenarArticulos(Connection c, ObservableList<Articulo> lista){
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
            "SELECT * FROM db_flowbase.tb_articulo");
//            "SELECT cod_articulo, "+
//                    "nombre, "+
//                    "descripcion, " +
//                    "costo, " +
//                    "color, "+ "FROM db_flowbase.tb_articulo");
            System.out.println("si");
            while(resultado.next()){
                lista.add(
                        new Articulo(
                        resultado.getString("cod_articulo"), 
                        resultado.getString("nombre"), 
                        resultado.getString("descripcion"), 
                        Float.parseFloat(resultado.getString("costo")), 
                        resultado.getString("color")));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
}
