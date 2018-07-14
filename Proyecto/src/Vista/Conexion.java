/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ROSA
 */
public class Conexion {
    private Connection connection ;
    private String url = "jdbc:mysql://localhost:3306/db_flowbase?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String pass = "rmpincay";
   
    public Connection getC() {
        return connection;
    }

    public void setC(Connection c) {
        this.connection = c;
    }
    
    public void connect(){
        System.out.println("Conectando...");
        try{
            this.connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    	
    public void cerrarConexion(){
	try {
            connection.close();
	} catch (SQLException e) {
            System.out.println(e.getMessage());
	}
    }
    
    public static void llenar(Connection c){
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
            "SELECT * FROM db_flowbase.tb_articulo");
            System.out.println("si");
            while(resultado.next()){
                String cadena=resultado.getString("color");
                String caden=resultado.getString("cod_articulo");
                System.out.println(cadena);
                System.out.println(caden);
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
    }
    
}
