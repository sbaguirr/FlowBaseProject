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

    public Articulo() {
       
    }
    
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
    
    public static void ingresarArticulo(String id, String nombre,String descripcion, float costo, String color,Connection c){
    try {
            String consulta= "insert into db_flowbase.tb_articulo values (?,?,?,?,?)";
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, id);
             ingreso.setString(2, nombre.toLowerCase());
             ingreso.setString(3, descripcion.toLowerCase());
             ingreso.setString(4, String.valueOf(costo));
             ingreso.setString(5, color.toLowerCase());
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("ingreso exitoso ...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
     public static Articulo buscarArticulo(String code, Connection e) {
        try {
            String consulta = "SELECT cod_producto, nombre, "+
                    "descripcion, " +
                    "costo, " +
                    "color, "+ "FROM db_flowbase.tb_articulo "
                    + "WHERE cod_producto = " + code;
            Statement in = e.createStatement();
            ResultSet resultado = in.executeQuery(consulta);
            ArrayList<String> t = new ArrayList();
            Articulo art = new Articulo();
            while(resultado.next()) {
                t.add(resultado.getString("t.telefono"));
                art.setCod_articulo(resultado.getString("cod_producto"));
                art.setNombre(resultado.getString("nombre"));
                art.setDescripcion(resultado.getString("descripcion"));
                art.setCosto(Float.parseFloat(resultado.getString("costo")));
                art.setColor(resultado.getString("color"));               
            }        
            return art;
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
        return null;
    }
    
}
