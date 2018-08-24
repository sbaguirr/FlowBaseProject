/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import controlador.VentanaDialogo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import javafx.scene.text.Font;
import modelo.Tb_pedido;

/**
 *
 * @author Tiffy
 */
public class PaneVerPedidos {

    private BorderPane root;
    private TableView tablaPedidos;
    private ObservableList<Tb_pedido> pedidos;
    private ComboBox<String> combo, comboEstado;
    private TextField campo;
    private Button filtrar;
    private DatePicker desde, entrega;
    private Conexion c;

    public PaneVerPedidos() {
        root = new BorderPane();
        c = new Conexion();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionFiltros();
        seccionCentro2();
        llamarObjetos();

    }

    public Pane getRoot() {
        return root;

    }

    private void llamarObjetos() {
        cargarCombo();
        cargarComboEstado();
        aplicarFiltros();
        back();
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Ver Pedidos");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        return h;
    }

    private void seccionFiltros() {
        VBox vb = new VBox();
        Label l = new Label("Buscar");
        Label f = new Label("Fecha del pedido");
        Label w = new Label("Estado del pedido");
        Label e = new Label("Fecha de entrega");
        GridPane gp = new GridPane();
        gp.addRow(0, l, combo, campo, f, desde, w, comboEstado, e, entrega, filtrar);
        gp.setHgap(10);
        gp.setVgap(5);
        vb.getChildren().addAll(encabezado(), gp);
        root.setTop(vb);
    }

    private void inicializarObjetos() {
        combo = new ComboBox();
        comboEstado = new ComboBox();
        combo.setPrefWidth(150);
        campo = new TextField();
        campo.setPromptText("Ci del vendedor");
        campo.setPrefWidth(150);
        filtrar = new Button("Filtrar");
        desde = new DatePicker();
        desde.setPrefWidth(110);
        entrega = new DatePicker();
        entrega.setPrefWidth(110);
        tablaPedidos = new TableView();
    }

    private void seccionCentro2() {
        VBox vb = new VBox();
        TableColumn c = new TableColumn<>("C");
        TableColumn fe = new TableColumn<>("Fecha Pedido");
        TableColumn obs = new TableColumn<>("Observaciones");
        TableColumn fee = new TableColumn<>("Fecha entrega");
        TableColumn hen = new TableColumn<>("Hora entrega");
        TableColumn cli = new TableColumn<>("Nombres Cliente");
        TableColumn cli2 = new TableColumn<>("Apellidos Cliente");
        TableColumn rem = new TableColumn<>("Vendedor");
        TableColumn tra = new TableColumn<>("Nombres Destinatario");
        TableColumn tra2 = new TableColumn<>("Apellidos Destinatario");
        TableColumn cant = new TableColumn<>("Estado");
        fe.setPrefWidth(100);
        fe.setCellValueFactory(new PropertyValueFactory<>("fecha_pedido"));
        propertiesTableView(fe);
        obs.setPrefWidth(150);
        obs.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        propertiesTableView(obs);
        fee.setPrefWidth(100);
        fee.setCellValueFactory(new PropertyValueFactory<>("fecha_entrega"));
        propertiesTableView(fee);
        hen.setPrefWidth(100);
        hen.setCellValueFactory(new PropertyValueFactory<>("hora_entrega"));
        propertiesTableView(hen);
        cli.setPrefWidth(100);
        cli.setCellValueFactory(new PropertyValueFactory<>("clienteNom"));
        propertiesTableView(cli);
        cli2.setPrefWidth(100);
        cli2.setCellValueFactory(new PropertyValueFactory<>("clienteApe"));
        propertiesTableView(cli2);
        rem.setPrefWidth(100);
        rem.setCellValueFactory(new PropertyValueFactory<>("ci_trabajador"));
        propertiesTableView(rem);

        tra.setPrefWidth(100);
        tra.setCellValueFactory(new PropertyValueFactory<>("destinatarioNom"));
        propertiesTableView(tra);
        tra2.setPrefWidth(100);
        tra2.setCellValueFactory(new PropertyValueFactory<>("destinatarioApe"));
        propertiesTableView(tra2);

        cant.setPrefWidth(100);
        cant.setCellValueFactory(new PropertyValueFactory<>("estado"));
        propertiesTableView(cant);

        c.setPrefWidth(25);
        c.setCellValueFactory(new PropertyValueFactory<>("cod_pedido"));
        propertiesTableView(c);
        tablaPedidos.getColumns().addAll(c, fe, fee, hen, cli, cli2, rem, tra, tra2, cant, obs);
        tablaPedidos.setPrefSize(1000, 600);
        vb.setPadding(new Insets(10, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().add(tablaPedidos);
        root.setCenter(vb);

    }

    /**
     * Método que cargará la información de los paises en el TableView
     */
    private void cargarFiltro1() {
        LocalDate f = desde.getValue();
        LocalDate g = entrega.getValue();
        if (f != null && g != null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorVendedor(seleccion2(), fecha(f), fecha(g), campo.getText(), c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        } else if (f != null && g == null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorVendedor(seleccion2(), fecha(f), null, campo.getText(), c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        } else if (f == null && g != null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorVendedor(seleccion2(), null, fecha(g), campo.getText(), c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        }

    }

    /**
     * Método que cargará la información de los paises en el TableView
     */
    private void cargarFiltro2() {
        LocalDate f = desde.getValue();
        LocalDate g = entrega.getValue();
        if (f != null && g != null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorFecha(seleccion2(), fecha(f), fecha(g), c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        } else if (f != null && g == null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorFecha(seleccion2(), fecha(f), null, c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        } else if (f == null && g != null) {
            c.connect();
            List<Tb_pedido> a = Tb_pedido.callObtenerPedidosPorFecha(seleccion2(), null, fecha(g), c.getC());
            c.cerrarConexion();
            pedidos = FXCollections.observableArrayList(a);
            tablaPedidos.setItems(pedidos);
        }

    }

    private String fecha(LocalDate f) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(f);
    }

    /**
     * Método que permite modificar una columna de un TableView
     *
     * @param c
     */
    private void propertiesTableView(TableColumn c) {
        c.setSortable(false);
        c.setResizable(true);
        c.setEditable(false);
    }

    private void aplicarFiltros() {
        filtrar.setOnAction(e -> {
            if (seleccion() != null && seleccion2() != null) {
                if (seleccion().equals("Todos")) {
                    cargarFiltro2();
                } else {
                    cargarFiltro1();
                }
            } else {
                VentanaDialogo.dialogoAdvertenciaPedido();
            }
        });
    }

    /**
     * De acuerdo a lo seleccionado en el combo, se aplicarán los filtros
     */
    private void cargarCombo() {
        String se[] = {"Todos", "Vendedor"};
        combo.setItems(FXCollections.observableArrayList(se));
    }

    /**
     * De acuerdo a lo seleccionado en el combo, se aplicarán los filtros
     */
    private void cargarComboEstado() {
        String se[] = {"Pendiente", "Procesado"};
        comboEstado.setItems(FXCollections.observableArrayList(se));
    }

    /**
     * Método que al seleccionar un comboBox devuelve un String
     *
     * @return g, String
     */
    private String seleccion() {
        return combo.getSelectionModel().getSelectedItem();
    }

    /**
     * Método que al seleccionar un comboBox devuelve un String
     *
     * @return g, String
     */
    private String seleccion2() {
        return comboEstado.getSelectionModel().getSelectedItem();
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
}
