/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

/**
 *
 * @author Tiffy
 */
public class PaneIngresarPedidos {

    private BorderPane root;
    private TextField cedula, nombres, dnombres, dapellidos, ddir, rdir, dtlf, cantidad, codigo,
            horario, estado, sub, tot;
    private TextArea mensaje, descrip;
    private Button buscar, realizar, verCliente, agregar, nuevo;
    private Label fechaActual, numPedido,vendedor;
    private ComboBox dia, mes, anio;
    //private ObservableList<String> listaProductos;
    private RadioButton si, no, efectivo, credito, debito;
    private TableView tablaPedido;

    public PaneIngresarPedidos() {
        root = new BorderPane();
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
        si = new RadioButton();
        si.setText("Si       ");
        no = new RadioButton();
        no.setText("No");
        hb.getChildren().addAll(si, no);

        VBox vb = new VBox();
        HBox fecha = new HBox();
        fecha.getChildren().addAll(dia, mes, anio);
        HBox comb = new HBox();
        comb.getChildren().addAll(dtlf, hEntrega, horario);
        comb.setSpacing(10);

        GridPane gp = new GridPane();
        gp.addColumn(0, Lcedula, Lnombre, Lentrega, Ldestino, Ldnombre, Ldireccion, Ltelefono);
        gp.addColumn(1, cedula, nombres, hb);
        gp.addColumn(3, verCliente);

        gp.add(dnombres, 1, 4);
        gp.add(ddir, 1, 5);
        gp.add(comb, 1, 6);
        gp.add(buscar, 2, 0);
        gp.add(Lapellido, 2, 4);
        gp.add(LRdireccion, 2, 5);
        gp.add(fEntrega, 2, 6);
        gp.add(dapellidos, 3, 4);
        gp.add(rdir, 3, 5);
        gp.add(fecha, 3, 6);

        Separator l = new Separator();
        l.setMaxWidth(850);
        gp.setVgap(5);
        gp.setHgap(10);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp, l);
        vb.setPadding(new Insets(10, 10, 5, 10));
        root.setTop(vb);
    }

    private void inicializarObjetos() {
        buscar = new Button();
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

        verCliente = new Button("Ver Cliente");
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/search.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        buscar.setBackground(Background.EMPTY);
        buscar.setContentDisplay(ContentDisplay.TOP);
        buscar.setGraphic(w);
        buscar.setPrefSize(20, 20);
        // nuevo = new Button("Nuevo Cliente");
        realizar = new Button("Realizar");

        dia = new ComboBox();
        dia.setDisable(true);
        mes = new ComboBox();
        mes.setDisable(true);
        anio = new ComboBox();
        anio.setDisable(true);

        descrip = new TextArea();
        descrip.setPrefWidth(100);
        descrip.setPrefHeight(30);
        mensaje = new TextArea();
        mensaje.setPrefWidth(100);
        mensaje.setPrefHeight(30);
        estado = new TextField();
        // estado.setPrefSize(50, 50);
        sub = new TextField();
        sub.setDisable(true);
        tot = new TextField();
        tot.setDisable(true);

        efectivo = new RadioButton();
        efectivo.setText("Efectivo     ");
        debito = new RadioButton();
        debito.setText("Débito     ");
        credito = new RadioButton();
        credito.setText("Crédito");
        
        vendedor= new Label("Nom usuario");
        fechaActual = new Label();
         numPedido = new Label();
    }

    private void seccionPedido() {
        VBox v = new VBox();
        GridPane gp = new GridPane();
        Date r = new Date();
        HBox hb = new HBox();
        HBox hb2 = new HBox();
        Label l = new Label("Fecha");
        Label ve = new Label("Vendedor");
        hb.setSpacing(5);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual.setText(dateFormat.format(r));///
        hb.getChildren().addAll(l, fechaActual);
         hb2.getChildren().addAll(ve, vendedor);
        numPedido.setText("N pedido 000N");
        Label LcodigoProducto = new Label("Código Producto");
        Label Lcantidad = new Label("Cantidad");
        codigo = new TextField();
        agregar = new Button("Agregar");
        cantidad = new TextField();
        gp.addRow(0, hb, numPedido,hb2);
        gp.addRow(1, LcodigoProducto, codigo, Lcantidad, cantidad, agregar);
        gp.setVgap(10);
        gp.setHgap(10);
        v.getChildren().addAll(gp, tablaArticulo(), seccionCentro());
        v.setPadding(new Insets(10, 10, 10, 10));
        root.setCenter(v);

    }

    private VBox tablaArticulo() {
        tablaPedido = new TableView();
        VBox vb = new VBox();
        TableColumn Tcodigo = new TableColumn<>("Código del producto");
        TableColumn Tdescripcion = new TableColumn<>("Descripción");
        TableColumn cost = new TableColumn<>("Costo");
        TableColumn cantidad = new TableColumn<>("Cantidad");
        Tcodigo.setPrefWidth(180);
        //Tcodigo.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(Tcodigo);
        Tdescripcion.setPrefWidth(300);
        //Tdescripcion.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(Tdescripcion);
        cost.setPrefWidth(150);
        //cost.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(cost);
        cantidad.setPrefWidth(110);
        //cantidad.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(cantidad);
        tablaPedido.getColumns().addAll(Tcodigo, Tdescripcion, cost, cantidad);
        tablaPedido.setPrefSize(50, 90); //ancho,alto

        vb.setPadding(new Insets(10, 10, 10, 0)); //top, derecha,abajo,izquierda
        vb.getChildren().addAll(tablaPedido);
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
        Label msj = new Label("Mensaje del Detalle");
        Label descripcion = new Label("Descripción/Observaciones");
        Label stado = new Label("Estado del pedido");
        Label forma = new Label("Forma de Pago");
        Label subtotal = new Label("Subtotal");
        Label total = new Label("Total");
        HBox g = new HBox();
        g.setSpacing(10);
        g.getChildren().addAll(efectivo, debito, credito);
        GridPane gp = new GridPane();
        gp.add(stado, 0, 0); // stado, estado, forma, g
        gp.add(estado, 1, 0);
        gp.add(forma, 0, 1);
        gp.add(g, 1, 1);
        gp.add(subtotal, 5, 0);
        gp.add(total, 5, 1);
        gp.add(sub, 6, 0);
        gp.add(tot, 6, 1);
        gp.add(realizar, 8, 1);
        gp.setVgap(5);
        gp.setHgap(5);
        vb.setPadding(new Insets(0, 10, 10, 0));
        vb.setSpacing(5);
        vb.getChildren().addAll(descripcion, descrip, msj, mensaje, gp);
        return vb;
    }

    private void llamarBotones() {
        //  buscarCliente();
        VerCliente();
        //  agregarProducto();
        // realizarPedido();
        back();
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
            MiniPaneCliente pm = new MiniPaneCliente();
            pm.showWindow();
        });
    }

    /**
     * VER LO DE LA LISTITA DE CODIGOS Este método agregará un nuevo producto a
     * la tabla pedido
     *
     * Validaciones: Que el campo código y cantidad no esten vacíos Que el
     * codigo ingresado exista
     */
    private void agregarProducto() {
        agregar.setOnAction(e -> {

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
            PaneMenuPrincipal p = new PaneMenuPrincipal();
            Proyecto.scene.setRoot(p.getRoot());
        });
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);
        root.setBottom(f);
    }

}
