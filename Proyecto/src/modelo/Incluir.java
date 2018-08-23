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
 * @author ROSA
 */
public class Incluir {
    private int id_pedido_articulo;
    private int cantidad;
    private String cod_articulo;
    private int cod_pedido;

    public Incluir(){}
    
    public Incluir(int id_pedido_articulo, int cantidad, String cod_articulo, int cod_pedido) {
        this.id_pedido_articulo = id_pedido_articulo;
        this.cantidad = cantidad;
        this.cod_articulo = cod_articulo;
        this.cod_pedido = cod_pedido;
    }
    
    public int getId_pedido_articulo() {
        return id_pedido_articulo;
    }

    public void setId_pedido_articulo(int id_pedido_articulo) {
        this.id_pedido_articulo = id_pedido_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public int getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    @Override
    public String toString() {
        return "Incluir{" + "id_pedido_articulo=" + id_pedido_articulo + ", cantidad=" + cantidad + ", cod_articulo=" + cod_articulo + ", cod_pedido=" + cod_pedido + '}';
    }
        
    public static void ingresarIncluye(int idPedidoArticulo, int cantidad, String codArticulo, int codPedido, Connection c){
    try {
            String consulta = "insert into db_flowbase.tb_pedido values (?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setInt(1, idPedidoArticulo);
            ingreso.setInt(2, cantidad);
            ingreso.setString(3, codArticulo);
            ingreso.setInt(4, codPedido);
            int j = ingreso.executeUpdate();
            if(j > 0){ 
                System.out.println("ingreso exitoso de incluir pedido...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION (incluir): " + ex.getMessage());
        }
    }
}
