/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javafx.collections.ObservableList;

/**
 *
 * @author Tiffy
 */
public class Tb_envio_articulo {
   private int id_envio_articulo;
    private String id_floreria;
    private String cod_articulo;
    private int stock;

    public Tb_envio_articulo(int id_envio_articulo, String floreria, String articulo, int stock) {
        this.id_envio_articulo = id_envio_articulo;
        this.id_floreria = floreria;
        this.cod_articulo = articulo;
        this.stock= stock;
    }

    public int getId_envio_articulo() {
        return id_envio_articulo;
    }

    public void setId_envio_articulo(int id_envio_articulo) {
        this.id_envio_articulo = id_envio_articulo;
    }

    public String getId_floreria() {
        return id_floreria;
    }

    public void setId_floreria(String id_floreria) {
        this.id_floreria = id_floreria;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

     public static int callModificarStockSucursal(int stock, String id_flor,int control ,String codigo, Connection c) {
        try {
            String consulta = "call modificarStockSucursal(?,?,?,?,?)";
             CallableStatement ingreso = c.prepareCall(consulta);
            ingreso.setInt(1, stock);
            ingreso.setString(2, id_flor);
             ingreso.setInt(3, control);
            ingreso.setString(4, codigo);
            ingreso.registerOutParameter(5, Types.INTEGER);
            ingreso.execute();
            int i= ingreso.getInt(5);
            ingreso.close();
            return i;
        } catch (SQLException ex) {
            System.out.println("EXCEPCION Envio_Articulo..: " + ex.getMessage());
        }
        return -1;
    }
     
    public static int callDescuentoStockMatriz(int stockIngresado, String cod, Connection c){
        int i=-1;
        try {
            String consulta = "{call descuentoStockMatriz(?,?,?)}"; 
            CallableStatement sp = c.prepareCall(consulta);
            sp.setInt(1, stockIngresado); //ingresar valor
            sp.setString(2,cod);
            sp.registerOutParameter(3, Types.INTEGER); //configura para que retorne
            sp.execute();
            i= sp.getInt(3);
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callDescuentoStock: " + ex.getMessage());
        }      
        return i;
    }
    
    
     public static void callRestaurarStockMatriz(int stockIngresado, String cod, Connection c){
        try {
            String consulta = "{call restaurarStockMatriz(?,?)}"; 
            CallableStatement sp = c.prepareCall(consulta);
            sp.setInt(1, stockIngresado); //ingresar valor
            sp.setString(2,cod);
            sp.execute();
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callRestaurarStock: " + ex.getMessage());
        }      
    }
    
    
     public static void llenarEnvios(ObservableList<Tb_envio_articulo> lista, Connection c){
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
            "SELECT e.id_envio_articulo, f.direccion, e.cod_articulo,e.stock FROM db_flowbase.tb_envio_articulo e join tb_floreria f on e.id_floreria= f.id_floreria");
            while(resultado.next()){
                lista.add(
                        new Tb_envio_articulo(
                        resultado.getInt("e.id_envio_articulo"),
                        resultado.getString("f.direccion"), 
                        resultado.getString("e.cod_articulo"), 
                        resultado.getInt("e.stock")
                        ));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION in llenar Tb_envio: " + ex.getMessage());
        }
    }
      
}

