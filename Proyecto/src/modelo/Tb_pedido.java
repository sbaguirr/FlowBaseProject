/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
    private Date fecha_pedido;
    private Time hora_pedido;
    private Date fecha_entrega;
    private Time hora_entrega;
    private String ci_trabajador;
    private String ci_cliente;
    private int cod_destinatario;
    private String clienteNom;
    private String destinatarioNom;
    private String clienteApe;
    private String destinatarioApe;

    public Tb_pedido(int cod_pedido, String observaciones, String mensaje, String forma_pago, double costo_total, String estado, Date fecha_pedido, Time hora_pedido, Date fecha_entrega, Time hora_entrega, String ci_trabajador, String ci_cliente, int cod_destinatario) {
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

    public Tb_pedido(int cod_pedido, String observaciones, String estado, Date fecha_pedido, Date fecha_entrega, Time hora_entrega, String ci_trabajador, String Nomcliente, String ApeCliente, String Nomdestinatario, String ApeDesti) {
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

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
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

    public Time getHora_pedido() {
        return hora_pedido;
    }

    public void setHora_pedido(Time hora_pedido) {
        this.hora_pedido = hora_pedido;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Time getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(Time hora_entrega) {
        this.hora_entrega = hora_entrega;
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
                                resultado.getDate("fecha_pedido"),
                                resultado.getTime("hora_pedido"),
                                resultado.getDate("fecha_entrega"),
                                resultado.getTime("hora_entrega"),
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

    public static List<Tb_pedido> callObtenerPedidosPorVendedor(String estado, Date fecP, Date fecE, Time hoE,String ci_vendedor, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta = "{call ObtenerPedidosPorVendedor(?,?,?,?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1, estado); //ingresar valor
            sp.setDate(2, (java.sql.Date) fecP);
            sp.setDate(3, (java.sql.Date) fecE);
            sp.setTime(4, hoE);
            sp.setString(5, ci_vendedor);
            sp.execute();
            final ResultSet resultado = sp.getResultSet();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getDate("p.fecha_pedido"),
                                resultado.getDate("p.fecha_entrega"),
                                resultado.getTime("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.nombres")));
            }
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callDescuentoStock: " + ex.getMessage());
        }
        return pw;
    }
    
    public static List<Tb_pedido> callObtenerPedidosPorFecha(String estado, Date fecP, Date fecE, Time hoE, Connection c) {
        List<Tb_pedido> pw = new ArrayList<>();
        try {
            String consulta = "{call ObtenerPedidosPorVendedor(?,?,?,?)}";
            CallableStatement sp = c.prepareCall(consulta);
            sp.setString(1, estado); //ingresar valor
            sp.setDate(2, (java.sql.Date) fecP);
            sp.setDate(3, (java.sql.Date) fecE);
            sp.setTime(4, hoE);
            sp.execute();
            final ResultSet resultado = sp.getResultSet();
            while (resultado.next()) {
                pw.add(
                        new Tb_pedido(
                                resultado.getInt("p.cod_pedido"),
                                resultado.getString("p.observaciones"),
                                resultado.getString("p.estado"),
                                resultado.getDate("p.fecha_pedido"),
                                resultado.getDate("p.fecha_entrega"),
                                resultado.getTime("p.hora_entrega"),
                                resultado.getString("p.ci_trabajador"),
                                resultado.getString("c.nombres"),
                                resultado.getString("c.apellidos"),
                                resultado.getString("d.nombres"),
                                resultado.getString("d.nombres")));
            }
            sp.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPCION callDescuentoStock: " + ex.getMessage());
        }
        return pw;
    }

}

