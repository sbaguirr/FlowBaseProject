/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import controlador.VentanaDialogo;
import static controlador.VentanaDialogo.PedidoGuardadoFallido;
import static controlador.VentanaDialogo.noNumerico;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modelo.Articulo;
import static modelo.Articulo.isNumeric;
import modelo.Tb_cliente;
import modelo.Tb_pedido;
import static modelo.Tb_pedido.ingPedido;
import static modelo.Tb_pedido.ingresarN1Pedido;
import static modelo.Tb_pedido.llenarPedidoArticulo;

/**
 *
 * @author Tiffy
 */
public class PaneIngresarPedidos {

    private BorderPane root;
    public static TextField codigo; 
    private TextField cedula, nombres, dnombres, dapellidos, ddir, rdir, dtlf, cantidad,
            horario, estado, sub, tot, cobro;
    private TextArea mensaje, descrip;
    private Button buscar, realizar, verCliente, agregar, nuevoCliente, verCodigo, eliminarCodigo;
    private Label fechaActual, numPedido, vendedor;
    private DatePicker fpedido;
    private ObservableList<Articulo> listaProductos;
    private ObservableList<String> listaCantPedido;
    private RadioButton si, no, efectivo, credito, debito, transferencia, paypal;
    private TableView<Articulo> tablaPedido;
    private Conexion c ;
    public static Tb_cliente f;
    public  static  int indicador;
    private TableColumn Tcodigo, cant, cost, Tdescripcion, Tname;


    public PaneIngresarPedidos() {
        root = new BorderPane();
        c = new Conexion();
        indicador=1;
        listaProductos = FXCollections.observableArrayList();
        listaCantPedido = FXCollections.observableArrayList();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionClienteDestinatario();
        seccionPedido();
        llamarBotones();
    }

    public Pane getRoot() {
        return root;
    }

    private void seccionClienteDestinatario() {
        Label Lcedula = new Label("Cédula");
        Label Lnombre = new Label("Cliente");
        Label Ldestino = new Label("Datos Destinatario");
        Label Ldnombre = new Label("Nombres");
        Label Lapellido = new Label("Apellidos");
        Label Ltelefono = new Label("Teléfono");
        Label Ldireccion = new Label("Dirección");
        Label LRdireccion = new Label("Referencias dirección");
        Label fEntrega = new Label("Fecha entrega");
        Label hEntrega = new Label("Horario entrega");

        //    HBox b = new HBox();
        //   b.getChildren().addAll(nuevo, verCliente);
        HBox hb = new HBox();
        Label Lentrega = new Label("Entrega");
        ToggleGroup grupo = new ToggleGroup();
        si = new RadioButton("Si       ");
        si.setToggleGroup(grupo);
        no = new RadioButton("No");
        no.setToggleGroup(grupo);
        hb.getChildren().addAll(si, no);

        VBox vb = new VBox();
        HBox comb = new HBox();
        comb.getChildren().addAll(dtlf, hEntrega, horario);
        comb.setSpacing(10);
        HBox botones = new HBox();
        botones.getChildren().addAll(verCliente, nuevoCliente);
        botones.setSpacing(10);
        botones.setPadding(new Insets(15, 0, 0, 0));
        GridPane gp = new GridPane();
        gp.addColumn(0, Lcedula, Lnombre, Lentrega, Ldestino, Ldnombre, Ldireccion, Ltelefono);
        gp.addColumn(1, cedula, nombres, hb);
        gp.addColumn(3, botones);

        gp.add(dnombres, 1, 4);
        gp.add(ddir, 1, 5);
        gp.add(comb, 1, 6);
        gp.add(buscar, 2, 0);
        gp.add(Lapellido, 2, 4);
        gp.add(LRdireccion, 2, 5);
        gp.add(fEntrega, 2, 6);
        gp.add(dapellidos, 3, 4);
        gp.add(rdir, 3, 5);
        gp.add(fpedido, 3, 6);

        Separator l = new Separator();
        l.setMaxWidth(850);
        gp.setVgap(4);
        gp.setHgap(10);
        vb.setSpacing(7);
        vb.getChildren().addAll(gp, l);
        vb.setPadding(new Insets(0, 10, 5, 10));
        root.setTop(vb);
    }

    private void inicializarObjetos() {
        buscar = new Button();
        nuevoCliente = new Button("Nuevo Cliente");
        nombres = new TextField();
        nombres.setDisable(true);
        nombres.setPrefWidth(300);
        cedula = new TextField();
        cedula.setPrefWidth(300);

        dnombres = new TextField();
        dnombres.setPrefWidth(300);
        dnombres.setDisable(true);
        dapellidos = new TextField();
        dapellidos.setPrefWidth(300);
        dapellidos.setDisable(true);
        ddir = new TextField();
        ddir.setPrefWidth(300);
        ddir.setDisable(true);
        rdir = new TextField();
        rdir.setDisable(true);
        dtlf = new TextField();
        dtlf.setPrefWidth(100);
        dtlf.setDisable(true);
        horario = new TextField();
        horario.setDisable(true);
        horario.setPrefWidth(100);
        cobro = new TextField();

        verCliente = new Button("Ver Cliente");
        verCliente.setDisable(true);
        verCodigo = new Button("...");
        verCodigo.setPrefSize(20, 20);

        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/search.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        buscar.setBackground(Background.EMPTY);
        buscar.setContentDisplay(ContentDisplay.TOP);
        buscar.setGraphic(w);
        buscar.setPrefSize(20, 20);

        realizar = new Button("Realizar");
        eliminarCodigo= new Button("Eliminar");
        descrip = new TextArea();
        descrip.setPrefWidth(500);
        descrip.setPrefHeight(30);
        mensaje = new TextArea();
        mensaje.setPrefWidth(500);
        mensaje.setPrefHeight(30);
        estado = new TextField();
        // estado.setPrefSize(50, 50);
        sub = new TextField();
        sub.setDisable(true);
        tot = new TextField();
        tot.setDisable(true);
        

        ToggleGroup grupo = new ToggleGroup();
        efectivo = new RadioButton("Efectivo     ");
        efectivo.setToggleGroup(grupo);
        debito = new RadioButton("Débito     ");
        debito.setToggleGroup(grupo);
        credito = new RadioButton("Crédito");
        credito.setToggleGroup(grupo);
        transferencia = new RadioButton("Transferencia");
        transferencia.setToggleGroup(grupo);
        paypal = new RadioButton("PayPal");
        paypal.setToggleGroup(grupo);

        vendedor = new Label();
        VerVendedor();

        fechaActual = new Label();
        numPedido = new Label();

        fpedido = new DatePicker();
        fpedido.setDisable(true);
    }

    private void eliminar() {
        eliminarCodigo.setOnAction((e) -> {
            try {
                Articulo w = tablaPedido.getSelectionModel().getSelectedItem();
                Iterator<Articulo> it = this.listaProductos.iterator();
                while(it.hasNext()){
                    if(w.getCod_articulo().equals(it.next().getCod_articulo())){
                        it.remove();                
                    }
                }
                tablaPedido.setItems(listaProductos);
                
                Iterator<Articulo> it1 = this.listaProductos.iterator();
                double valorSubT = 0;
                while(it1.hasNext()){
                    Articulo a = it1.next();
                    valorSubT = valorSubT + a.getCosto()*Integer.valueOf(a.getCant());
                }
                sub.setText(String.valueOf(Math.round(valorSubT*100d)/ 100d));
                double iva = Double.valueOf(sub.getText())*0.12;
                double subT = Double.valueOf(sub.getText());
                tot.setText(String.valueOf( Math.round( (iva + subT) * 100d ) / 100d) );
            } catch (Exception ex) {
                System.out.println("Exception (eliminar)");   
            }
        });
    }
    
    private void seccionPedido() {
        VBox v = new VBox();
        GridPane gp = new GridPane();
        Date r = new Date();
        HBox hb = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        Label l = new Label("Fecha");
        Label ve = new Label("Vendedor");
        Label l3 = new Label("N° pedido ");
        hb.setSpacing(5);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaActual.setText(dateFormat.format(r));
        hb.getChildren().addAll(l, fechaActual);
        hb2.getChildren().addAll(ve, vendedor);
        hb2.setSpacing(5);
        c.connect();
        numPedido.setText(String.valueOf(Tb_pedido.buscarLastPedido(c.getC()))); 
        c.cerrarConexion();
        hb3.getChildren().addAll(l3, numPedido);
        hb3.setSpacing(5);
        Label LcodigoProducto = new Label("Código Producto");
        Label Lcantidad = new Label("Cantidad");
        codigo = new TextField();
        codigo.setDisable(true); 
        codigo.setPrefWidth(100);
        HBox conjunto = new HBox();
        conjunto.getChildren().addAll(codigo, verCodigo);
        agregar = new Button("Agregar");
        cantidad = new TextField();
        gp.addRow(0, hb, hb3, hb2);
        gp.addRow(1, LcodigoProducto, conjunto, Lcantidad, cantidad,agregar);
        gp.setVgap(10);
        gp.setHgap(10);
        v.getChildren().addAll(gp, tablaArticulo(), seccionCentro());
        v.setPadding(new Insets(10, 10, 10, 10));
        root.setCenter(v);

    }

    private VBox tablaArticulo() {
        tablaPedido = new TableView();
        VBox vb = new VBox();
        Tcodigo = new TableColumn<>("Código del producto");
        cant = new TableColumn<>("Cantidad");
        Tname = new TableColumn<>("Nombre");
        Tdescripcion = new TableColumn<>("Descripción");
        cost = new TableColumn<>("Costo");
        //color = new TableColumn<>("Color");
        Tcodigo.setPrefWidth(180);
        Tcodigo.setCellValueFactory(new PropertyValueFactory<>("cod_articulo"));
        propertiesTableView(Tcodigo);
        
        Tname.setPrefWidth(300);
        Tname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        propertiesTableView(Tname);
        
        Tdescripcion.setPrefWidth(300);
        Tdescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        propertiesTableView(Tdescripcion);
        
        cost.setPrefWidth(150);
        cost.setCellValueFactory(new PropertyValueFactory<>("costo"));
        propertiesTableView(cost);
        
        cant.setPrefWidth(110);
        cant.setCellValueFactory(new PropertyValueFactory<>("cant"));
        listaCantPedido.add(cantidad.getText());
        propertiesTableView(cant);
        tablaPedido.setPrefSize(50, 210); //ancho,alto
        
        agregar.setOnAction(e->{
            c.connect();
            if(isNumeric(cantidad.getText())){
                String texto = (cantidad.getText());
            llenarPedidoArticulo(c.getC(), codigo.getText(), texto, listaProductos);           
            tablaPedido.setItems(listaProductos);
            cantidad.setText("");
            c.cerrarConexion();
            }else{
                noNumerico();
            } 
            Iterator<Articulo> it = this.listaProductos.iterator();
            double valorSubT = 0;
            while(it.hasNext()){
                Articulo a = it.next();
                valorSubT = valorSubT + a.getCosto()*Integer.valueOf(a.getCant());
            }
            sub.setText(String.valueOf(Math.round(valorSubT*100d)/ 100d));
            double iva = Double.valueOf(sub.getText())*0.12;
            double subT = Double.valueOf(sub.getText());
            tot.setText(String.valueOf( Math.round( (iva + subT) * 100d ) / 100d) );
        });
        tablaPedido.getColumns().addAll(Tcodigo, Tname, Tdescripcion, cost, cant);
        vb.setSpacing(2);
        vb.setPadding(new Insets(5, 0, 5, 0)); //top, derecha,abajo,izquierda
        vb.getChildren().addAll(tablaPedido, eliminarCodigo);
       
        return vb;
    }
        
    /**
     * Método que permite modificar una columna de un TableView
     *
     * @param c
     */
    private void propertiesTableView(TableColumn c) {
        c.setSortable(false);
        c.setResizable(false);
        c.setEditable(false);
    }

    private VBox seccionCentro() {
        VBox vb = new VBox();
        HBox descYmsj = new HBox();
        VBox desc = new VBox();
        VBox mesj = new VBox();
        Label msj = new Label("Mensaje del Detalle");
        Label descripcion = new Label("Descripción/Observaciones");
        Label stado = new Label("Estado del pedido");
        Label forma = new Label("Forma de Pago");
        Label hcobro = new Label("Hora de cobro");
        Label subtotal = new Label("Subtotal");
        Label total = new Label("Total");
        HBox g = new HBox();
        g.setSpacing(10);
        g.setPadding(new Insets(0.5, 0, 0, 0));
        g.getChildren().addAll(efectivo, debito, credito, transferencia, paypal);
        GridPane gp = new GridPane();
        gp.add(stado, 0, 0); // stado, estado, forma, g
        gp.add(estado, 1, 0);
        gp.add(forma, 0, 1);
        gp.add(hcobro, 0, 2);
        gp.add(g, 1, 1);
        gp.add(cobro, 1, 2);
        gp.add(subtotal, 5, 0);
        gp.add(total, 5, 1);
        gp.add(sub, 6, 0);
        gp.add(tot, 6, 1);
        gp.add(realizar, 8, 1);
        gp.setVgap(5);
        gp.setHgap(5);
        vb.setPadding(new Insets(0, 0, 1, 0));
        vb.setSpacing(4);
        desc.setSpacing(3);
        mesj.setSpacing(3);
        desc.getChildren().addAll(descripcion, descrip);
        mesj.getChildren().addAll(msj, mensaje);
        descYmsj.setSpacing(75);
        descYmsj.getChildren().addAll(desc, mesj);
        vb.getChildren().addAll(descYmsj, gp);
        return vb;
    }

    private void llamarBotones() {
        buscarCliente();
        VerCliente();
        NuevoCliente();
        habilitarDestinatario();
        deshabilitarDestinatario();
        Vercodigos();
        eliminar();
        realizarPedido();
        back();
    }

    private void habilitarDestinatario() {
        si.setOnAction(e -> {
            habilitarCasillas();
        });
    }

    private void deshabilitarDestinatario() {
        no.setOnAction(e -> {
            deshabilitarCasillas();
        });
    }

    private void habilitarCasillas() {
        dnombres.setDisable(false);
        dapellidos.setDisable(false);
        ddir.setDisable(false);
        rdir.setDisable(false);
        dtlf.setDisable(false);
        fpedido.setDisable(false);
        horario.setDisable(false);
    }

    private void deshabilitarCasillas() {
        dnombres.setDisable(true);
        dapellidos.setDisable(true);
        ddir.setDisable(true);
        rdir.setDisable(true);
        dtlf.setDisable(true);
        fpedido.setDisable(true);
        horario.setDisable(true);
    }

    /**
     * Este método buscará el cliente según su número de cédula, si existe, se
     * llenará la casilla con el nombre del cliente si no existe, se mostrará la
     * ventana de MiniPaneCliente, para que el usuario llene los datos del nuevo
     * cliente
     *
     * Validaciones: Que el campo cédula no esté vacío
     */
    private void buscarCliente() {
        buscar.setOnAction(e -> {
            if (!cedula.getText().equals("")) {
                c.connect();
                f = Tb_cliente.buscarCliente(cedula.getText(), c.getC());
                c.cerrarConexion();
                if (f.getCi_cliente() != null) {
                    String k = f.getNombres() + " " + f.getApellidos();
                    nombres.setText(k);
                    verCliente.setDisable(false);
                } else {
                    VentanaDialogo.VentanaRegistroNoEncontrado();
                    nombres.setText("");
                }
            } else {
                VentanaDialogo.dialogoAdvertencia();
                nombres.setText("");
            }
        });
    }

    /**
     * Este botón me permitirá obtener el codigo de la lista de productos
     */
    private void Vercodigos() {
        verCodigo.setOnAction(e -> {
            MiniPaneArticulo mpp = new MiniPaneArticulo();
            mpp.showWindow();
        });
    }

    /**
     * Este método llamará a la vista MiniPaneCliente y mostrará la información
     * del cliente (siempre y cuando se haya escrito la cédula y el cliente
     * exista) Permitirá actualizar los datos del cliente.
     *
     * Validaciones: Que el campo cédula no esté vacío
     */
    private void VerCliente() {
        verCliente.setOnAction(e -> {
            if (!cedula.getText().equals("") && f.getCi_cliente() != null) {
                MiniPaneCliente pk = new MiniPaneCliente();
                MiniPaneCliente.guardar.setDisable(true);
                pk.cargarDatos();
                pk.showWindow();
            } else {
                VentanaDialogo.dialogoAdvertencia();
            }
        });
    }

    /**
     * Agregar un nuevo cliente
     */
    private void NuevoCliente() {
        nuevoCliente.setOnAction(e -> {
            MiniPaneCliente pk = new MiniPaneCliente();
            MiniPaneCliente.modificar.setDisable(true);
            pk.showWindow();
        });
    }

    /**
     * Este método guardará el pedido Validaciones: Al menos el campo cedula con
     * datos, productos ingresados(?)
     */
    private void realizarPedido() {
        realizar.setOnAction(e -> {
           
        });
    }
    
    private String fecha(LocalDate f) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(f);
    } 

    /**
     * Este metodo coloca la ci del vendedor que ha iniciado sesion
     */
    private void VerVendedor() {
        if (PaneMenuPrincipal.nombreUsuario.getText().equals("") && !PaneMenuPrincipalSucursal.nombreUsuarioSucursal.getText().equals("")) {
            vendedor.setText(PaneMenuPrincipalSucursal.nombreUsuarioSucursal.getText());
        } else {
            PaneMenuPrincipal p = new PaneMenuPrincipal();
            vendedor.setText(PaneMenuPrincipal.nombreUsuario.getText());
        }
    }

    private void back() {
        HBox f = new HBox();
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/left-arrow.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        Button back = new Button();
        back.setBackground(Background.EMPTY);
        back.setPrefSize(15, 15);
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(w);
        back.setOnAction(e -> {
            if (PaneMenuPrincipal.nombreUsuario.getText().equals("") && !PaneMenuPrincipalSucursal.nombreUsuarioSucursal.getText().equals("")) {
                PaneMenuPrincipalSucursal p = new PaneMenuPrincipalSucursal();
                Proyecto.scene.setRoot(p.getRoot());
            } else {
                PaneMenuPrincipal p = new PaneMenuPrincipal();
                Proyecto.scene.setRoot(p.getRoot());
            }
        });
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);
        root.setBottom(f);
    }
    
    private boolean validar() {
    return !codigo.getText().equals("") 
            && !this.cedula.getText().equals("") && !this.estado.getText().equals("");
    }
    
    private void limpiarCampos() {
        codigo.setText("");
        cantidad.setText("");
        cedula.setText("");
        estado.setText("");
    }
}