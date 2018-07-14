/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Vista.Conexion;
import static Vista.Conexion.llenar;

/**
 *
 * @author ROSA
 */
public class TestConexion {
    
    public static void main(String[] args){
        Conexion e = new Conexion();
        e.connect(); //metodo que estabece la conexión
        llenar(e.getC()); //metodo para extraer datos de la base
    }
}
