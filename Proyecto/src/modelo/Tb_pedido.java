/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static controlador.VentanaDialogo.PedidoGuardadoExitosamente;
import static controlador.VentanaDialogo.PedidoGuardadoFallido;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Tiffy
 */
public class Tb_pedido {

    private int cod_pedido;
    private String observaciones;
    private String mensaje;
    private String forma_pago;
    private double costo_total;
    private String estado;
    private String fecha_pedido;
    private String hora_pedido;
    private String fecha_entrega;
    private String hora_entrega;
    private String ci_trabajador;
    private String ci_cliente;
    private int cod_destinatario;
    private String clienteNom;
    private String destinatarioNom;
    private String clienteApe;
    private String destinatarioApe;
    private String cant;

    public Tb_pedido(int cod_pedido, String observaciones, String mensaje, String forma_pago, double costo_total, String estado, String fecha_pedido, String hora_pedido, String fecha_entrega, String hora_entrega, String ci_trabajador, String ci_cliente, int cod_destinatario) {
        this.cod_pedido = cod_pedido;
        this.observaciones = observaciones;
        this.mensaje = mensaje;
        this.forma_pago = forma_pago;
        this.costo_total = costo_total;
        this.estado = estado;
        this.fecha_pedido = fecha_pedido;
        this.hora_pedido = hora_pedido;
        this.fecha_entrega = fecha_entrega;
        this.hora_entrega = hora_entrega;
        this.ci_trabajador = ci_trabajador;
        this.ci_cliente = ci_cliente;
        this.cod_destinatario = cod_destinatario;
    }

    public Tb_pedido(int cod_pedido, String observaciones, String estado, String fecha_pedido, String fecha_entrega, String hora_entrega, String ci_trabajador, String Nomcliente, String ApeCliente, String Nomdestinatario, String ApeDesti) {
        this.cod_pedido = cod_pedido;
        this.observaciones = observaciones;
        this.estado = estado;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.hora_entrega = hora_entrega;
        this.ci_trabajador = ci_trabajador;
        this.clienteNom = Nomcliente;
        this.clienteApe = ApeCliente;
        this.destinatarioNom = Nomdestinatario;
        this.destinatarioApe = ApeDesti;
    }

    public int getCod_pedido() {
        return cod_pedido;
    }

    public String getClienteNom() {
        return clienteNom;
    }

    public void setClienteNom(String clienteNom) {
        this.clienteNom = clienteNom;
    }

    public String getDestinatarioNom() {
        return destinatarioNom;
    }

    public void setDestinatarioNom(String destinatarioNom) {
        this.destinatarioNom = destinatarioNom;
    }

    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getClienteApe() {
        return clienteApe;
    }

    public void setClienteApe(String clienteApe) {
        this.clienteApe = clienteApe;
    }

    public String getDestinatarioApe() {
        return destinatarioApe;
    }

    public void setDestinatarioApe(String destinatarioApe) {
        this.destinatarioApe = destinatarioApe;
    }

    public String getHora_pedido() {
        return hora_pedido;
    }

    public void setHora_pedido(String hora_pedido) {
        this.hora_pedido = hora_pedido;
    }

    public String getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(String hora_entrega) {
        this.hora_entrega = hora_entrega;
    }

   

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

   

    public String getCi_trabajador() {
        return ci_trabajador;
    }

    public void setCi_trabajador(String ci_trabajador) {
        this.ci_trabajador = ci_trabajador;
    }

    public String getCi_cliente() {
        return ci_cliente;
    }

    public void setCi_cliente(String ci_cliente) {
        this.ci_cliente = ci_cliente;
    }

    public int getCod_destinatario() {
        return cod_destinatario;
    }

    public void setCod_destinatario(int cod_destinatario) {
        this.cod_destinatario = cod_destinatario;
    }

    public static void llenarPedidos(ObservableList<Tb_pedido> lista, Connection c) {
        try {
            Statement in = c.createStatement();
            ResultSet resultado = in.executeQuery(
                    "SELECT * FROM db_flowbase.tb_pedido");
            while (resultado.next()) {
                lista.add(
                        new Tb_pedido(
                                resultado.getInt("cod_pedido"),
                                resultado.getString("observaciones"),
                                resultado.getString("mensaje"),
                                resultado.getString("forma_pago"),
                                resultado.getDouble("costo_total"),
                                resultado.getString("estado"),
                                resultado.getString("fecha_pedido"),
                                resultado.getString("hora_pedido"),
                                resultado.getString("fecha_entrega"),
                                resultado.getString("hora_entrega"),
                                resultado.getString("ci_trabajador"),
                                resultado.getString("ci_cliente"),
                                resultado.getInt("cod_destinatario")
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION in llenar Tb_envio: " + ex.getMessage());
        }
    }
    
    public static List<Tb_pedido> callObtenerPedidosPorVendedor(String estado, String fecP, String fecE, String ci_vendedor, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta = "{call ObtenerPedidosPorVendedor(?,?,?,?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1, estado); //ingresar valor
            sp.setString(2, ci_vendedor);
            sp.setString(3, fecP);
            sp.setString(4, fecE);

            sp.execute();
            final ResultSet resultado = sp.getResultSet();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getString("p.fecha_pedido"),
                                resultado.getString("p.fecha_entrega"),
                                resultado.getString("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.apellidos")));
            }
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callVendedor: " + ex.getMessage());
        }
        return pw;
    }

    //in estado varchar(15), in fecP date, in fecE date
    public static List<Tb_pedido> callObtenerPedidosPorFecha(String estado, String fecP, String fecE, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta = "{call ObtenerPedidosPorFecha(?,?,?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1, estado); 
            sp.setString(2, fecP);
            sp.setString(3, fecE);
            sp.execute();
            final ResultSet resultado = sp.getResultSet();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getString("p.fecha_pedido"),
                                resultado.getString("p.fecha_entrega"),
                                resultado.getString("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.apellidos")));
            }
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callPedidos: " + ex.getMessage());
        }
        return pw;
    }
    
     public static int proxPedido(Connection c) {
        int numero = -2;
        try {
            String consulta = "{call obtenerUltimoPedido(?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.registerOutParameter(1, Types.INTEGER);
            sp.execute();
            numero= sp.getInt(1);
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callProxPedido: " + ex.getMessage());
        }
        return numero+1;

    }
   
    public static List<Tb_pedido> pedidosXClienteCi(String ci, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta
                    = "select p.cod_pedido,p.observaciones,p.estado,p.fecha_pedido, p.fecha_entrega, p.hora_entrega, p.ci_trabajador, c.nombres, c.apellidos, d.nombres, d.apellidos "
                    + "from tb_pedido p "
                    + "join "
                    + "tb_cliente c on p.ci_cliente= c.ci_cliente "
                    + "join "
                    + "tb_destinatario d on p.cod_destinatario= d.cod_destinatario "
                    + "where "
                    + "c.ci_cliente=?";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setString(1, ci);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getString("p.fecha_pedido"),
                                resultado.getString("p.fecha_entrega"),
                                resultado.getString("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.apellidos")));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION ClientexPedido: " + ex.getMessage());
        }
        return pw;
    }

     public static List<Tb_pedido> pedidosXClienteNom(String nom,String ap, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta
                    = "select p.cod_pedido,p.observaciones,p.estado,p.fecha_pedido, p.fecha_entrega, p.hora_entrega, p.ci_trabajador, c.nombres, c.apellidos, d.nombres, d.apellidos "
                    + "from tb_pedido p "
                    + "join "
                    + "tb_cliente c on p.ci_cliente= c.ci_cliente "
                    + "join "
                    + "tb_destinatario d on p.cod_destinatario= d.cod_destinatario "
                    + "where "
                    + "c.nombres=? and c.apellidos=? " ;
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setString(1, nom);
            ingreso.setString(2, ap);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getString("p.fecha_pedido"),
                                resultado.getString("p.fecha_entrega"),
                                resultado.getString("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.apellidos")));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION ClientexPedido: " + ex.getMessage());
        }
        return pw;
    }
    @Override
    public String toString() {
        return cod_pedido + observaciones + mensaje + forma_pago + costo_total + 
               estado + fecha_pedido + hora_pedido + fecha_entrega + hora_entrega + 
               ci_trabajador + ci_cliente + cod_destinatario + clienteNom + 
               destinatarioNom + clienteApe + destinatarioApe;
    }
    
    public static void ingresarPedido(String obs, String msj, String formaPago,
        double costoTot, String estado, LocalDate fechaP, LocalTime horaP, LocalDate fechaE, 
        LocalTime horaE, String ci_trabajador, String ci_cliente, int codDestinat, Connection c){
        try {
            String consulta = "insert into db_flowbase.tb_pedido(observaciones, "
                    + "mensaje, forma_pago, costo_total, estado, fecha_pedido, "
                    + "hora_pedido, fecha_entrega, hora_entrega, ci_trabajador, "
                    + "ci_cliente, cod_destinatario) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            
            ingreso.setString(1, obs.toLowerCase());
            ingreso.setString(2, msj.toLowerCase());
            ingreso.setString(3, formaPago.toLowerCase());
            ingreso.setDouble(4, Math.round(costoTot*100d)/ 100d);
            ingreso.setString(5, estado.toLowerCase());
            ingreso.setDate(6, java.sql.Date.valueOf(fechaP));
            ingreso.setTime(7, java.sql.Time.valueOf(horaP));
            ingreso.setDate(8, java.sql.Date.valueOf(fechaE));
            ingreso.setTime(9, java.sql.Time.valueOf(horaE));
            ingreso.setString(10, ci_trabajador);
            ingreso.setString(11, ci_cliente);
            ingreso.setInt(12, codDestinat);
            int p = ingreso.executeUpdate();
            if(p > 0){ 
                System.out.println("ingreso exitoso de pedido con destinatario...");
                PedidoGuardadoExitosamente();
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION (pedido destinatario): " + ex.getMessage());
        }
    }
    
    public static void llenarPedidoArticulo(Connection c, String code, String cant, ObservableList<Articulo> lista){
        try {
            String consulta = "SELECT * FROM db_flowbase.tb_articulo where cod_articulo =?";
            PreparedStatement buscar = c.prepareStatement(consulta);
            buscar.setString(1, code);
            ResultSet resultado = buscar.executeQuery();
            
            System.out.println("si");
            while(resultado.next()){
                lista.add(
                        new Articulo(
                        resultado.getString("cod_articulo"), 
                        resultado.getString("nombre"), 
                        resultado.getString("descripcion"), 
                        Float.parseFloat(resultado.getString("costo")), 
                        resultado.getString("color"), cant));
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION (pedido articulo): " + ex.getMessage());
        }
    }
    
     public static int buscarLastPedido(Connection e) {
        int pedido = 1;
        try {
            String consulta = "SELECT cod_pedido " +
                                "FROM db_flowbase.tb_pedido " +
                                "order by cod_pedido desc " +
                                "limit 1;";
            Statement in = e.createStatement();
            ResultSet resultado = in.executeQuery(consulta);
            if(resultado.next()) {
                pedido = pedido + resultado.getInt("cod_pedido");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION   : " + ex.getMessage());
        }
        return pedido;
    }
     
    public static void ingresarNPedido(String obs, String msj, String formaPago,
        double costoTot, String estado, LocalDate fechaP, LocalTime horaP, 
        String ci_trabajador, String ci_cliente, Connection c){
        try {
            String consulta = "insert into db_flowbase.tb_pedido(observaciones,mensaje,forma_pago,costo_total, estado, fecha_pedido,hora_pedido,ci_trabajador,ci_cliente)  values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ingreso = c.prepareStatement(consulta);
            ingreso.setString(1, obs);
            ingreso.setString(2, msj);
            ingreso.setString(3, formaPago);
            ingreso.setDouble(4, Math.round(costoTot*100d)/ 100d);
            ingreso.setString(5, estado);
            ingreso.setDate(6, java.sql.Date.valueOf(fechaP));
            ingreso.setTime(7, java.sql.Time.valueOf(horaP));
            ingreso.setString(8, ci_trabajador);
            ingreso.setString(9, ci_cliente);
            int p = ingreso.executeUpdate();
            if(p > 0){ 
                System.out.println("ingreso exitoso de pedido...");
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPCION(pedido): " + ex.getMessage());
        }
    }  
    
}

