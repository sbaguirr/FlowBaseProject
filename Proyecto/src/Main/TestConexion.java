/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Vista.Conexion;
import static Vista.Conexion.llenar;
import modelo.Tb_cliente;

/**
 *
 * @author ROSA
 */
public class TestConexion {
    
    public static void main(String[] args){
        Conexion e = new Conexion();
        e.connect(); //metodo que estabece la conexión
        llenar(e.getC()); //metodo para extraer datos de la base
        System.out.println(Tb_cliente.buscarCliente("0908102940", e.getC()));
        //  Conexion.ejemploParaInsertar(e.getC(), "09081029407", "Eragon INC", "av los esteros", "2122549", "0908102940");
       // Conexion.ejemploParaActualizar(e.getC(), "09081029407", "PRUEBA", "PRUEBA", "PRUEBA", "0908102940");
        e.cerrarConexion(); //No se olviden de cerrar la conexion siempre, creo que hay que ponerla en un finally del try
    }
}
