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
    private int id_floreria;
    private String cod_articulo;
    private int stock;
    private int stockInicial;

    public Tb_envio_articulo(int id_envio_articulo, int floreria, String articulo, int stock, int stockInicial) {
        this.id_envio_articulo = id_envio_articulo;
        this.id_floreria = floreria;
        this.cod_articulo = articulo;
        this.stock= stock;
        this.stockInicial=stockInicial;
    }

    public int getId_envio_articulo() {
        return id_envio_articulo;
    }

    public void setId_envio_articulo(int id_envio_articulo) {
        this.id_envio_articulo = id_envio_articulo;
    }

    public int getId_floreria() {
        return id_floreria;
    }

    public void setId_floreria(int id_floreria) {
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

    public int getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

     public static void ingresarEnvio_Articulo(int stock, int id_flor, String codigo,int stockI, Connection c) {
        try {
            String consulta = "insert into db_flowbase.tb_envio_articulo(stock,id_floreria,cod_articulo,stockInicial) values (?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setInt(1, stock);
            ingreso.setInt(2, id_flor);
            ingreso.setString(3, codigo);
            ingreso.setInt(4, stockI);
            int j = ingreso.executeUpdate();
            if (j > 0) { //BORRAR LUEGO
                System.out.println("ingreso exitoso Envio_Articulo...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION Envio_Articulo..: " + ex.getMessage());
        }
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
    
     public static void llenarEnvios(ObservableList<Tb_envio_articulo> lista, Connection c){
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
            "SELECT * FROM db_flowbase.tb_envio_articulo");
            while(resultado.next()){
                lista.add(
                        new Tb_envio_articulo(
                        resultado.getInt("id_envio_articulo"),
                        resultado.getInt("id_floreria"), 
                        resultado.getString("cod_articulo"), 
                        resultado.getInt("stock"), 
                        resultado.getInt("stockInicial")));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION in llenar Tb_envio: " + ex.getMessage());
        }
    }
      
}

