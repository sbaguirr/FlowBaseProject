/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static Vista.PaneModificarArticulos.*;
import static controlador.VentanaDialogo.VentanaRegistroNoEncontrado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

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
    private static PreparedStatement modifica;

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
            if(j > 0){ //BORRAR LUEGO
                System.out.println("ingreso exitoso ...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
    public static String buscarArticuloXCodigo(String code, Connection e) {
        try {
            String consulta = "SELECT cod_articulo FROM db_flowbase.tb_articulo "
                    + "WHERE cod_articulo = ?"+ code;
            if(consulta == null){
                return null;
            }else{
            Statement in = e.createStatement();
            ResultSet resultado = in.executeQuery(consulta);
            String art = null;
            while(resultado.next()) {
                art = resultado.getString("cod_articulo");               
            }
            return art;  
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
        return null;
    }
    
    public static void buscarArticulo(String code, Connection e) {
        try {
            String consulta = "SELECT nombre,descripcion,costo,color "
                    + "FROM db_flowbase.tb_articulo "
                    + "WHERE cod_articulo = ?";
            PreparedStatement buscar = e.prepareStatement(consulta);
            buscar.setString(1, code);
            ResultSet resultado = buscar.executeQuery();
            if(resultado.next()) {
                txt_nombre.setText(resultado.getString("nombre"));               
                txt_descripcion.setText(resultado.getString("descripcion"));               
                txt_costo.setText(String.valueOf(resultado.getString("costo")));               
                txt_color.setText(resultado.getString("color"));
            }else{
                VentanaRegistroNoEncontrado();
                txt_nombre.setDisable(true);
                txt_descripcion.setDisable(true);
                txt_costo.setDisable(true);
                txt_color.setDisable(true);
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
    public static void eliminarArticulo(String code, Connection e) {
        try {
            String consulta = "DELETE FROM tb_articulo WHERE cod_articulo = ?";
            PreparedStatement eliminar = e.prepareStatement(consulta);
            eliminar.setString(1, code);
            int d = eliminar.executeUpdate();
            if(d > 0){ 
                 System.out.println("borrado exitoso ...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
    public static void modificarArticulo(String code, String nombre, String desc, float costo, String color, Connection e) {
        try {
            String consulta = "UPDATE tb_articulo SET nombre = ?,descripcion=?, "
                    + "costo =?, color=?  WHERE cod_articulo = ?";
            modifica = e.prepareStatement(consulta);
            modifica.setString(1, nombre);
            modifica.setString(2, desc);
            modifica.setString(3, String.valueOf(costo));
            modifica.setString(4, color);
            modifica.setString(5, code);
            int m = modifica.executeUpdate();
            if( m > 0){ 
                System.out.println("modificación exitosa ...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
    
}
