/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private String pass = "rmpincay";  //rmpincay
   
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
    
    public static void ejemploParaInsertar(Connection c,String a,String b,String f,String d,String o) {
        try {
            String consulta= "insert into db_flowbase.tb_referencias values (?,?,?,?,?)";
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, a);
             ingreso.setString(2, b);
             ingreso.setString(3, f);
             ingreso.setString(4, d);
             ingreso.setString(5, o);
             int j= ingreso.executeUpdate();
             if(j>0){ //BORRAR LUEGO
                 System.out.println("ingreso exitoso...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
      //  return 0;
    }
    
    public static void ejemploParaActualizar(Connection c,String a,String b,String f,String d,String o){
    try {
            String consulta= "update db_flowbase.tb_referencias set nombre_empresa=?,direccion_empresa=?,telefono_empresa=?,ci_cliente=? where ruc="+a;
             PreparedStatement ingreso = c.prepareStatement(consulta);
             ingreso.setString(1, b);
             ingreso.setString(2, f);
             ingreso.setString(3, d);
             ingreso.setString(4, o);
             int j= ingreso.executeUpdate();
             if(j>0){
                 System.out.println("actualizacion exitosa...");
             }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION: " + ex.getMessage());
        }
       // return 0;  
    }
    
}
