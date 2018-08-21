/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import controlador.VentanaDialogo;
import javafx.collections.FXCollections;
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

/**
 *
 * @author Tiffy
 */
public class PaneVerPedidos {

    private BorderPane root;
    private TableView tablaPedidos;
    private ComboBox<String> combo, comboEstado;
    private TextField campo;
    private Spinner<Integer> hora, minutos;
    private Button filtrar;
    private DatePicker desde, entrega;

    public PaneVerPedidos() {
        root = new BorderPane();
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
        Label p = new Label(":");
        HBox ni = new HBox();
        ni.getChildren().addAll(hora, p, minutos);
        ni.setSpacing(5);
        GridPane gp = new GridPane();
        gp.addRow(0, l, combo, campo, f, desde, w, comboEstado);
        gp.addRow(1, e, entrega, ni, filtrar);
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
        campo.setPrefWidth(150);
        filtrar = new Button("Aplicar filtros");
        desde = new DatePicker();
        desde.setPrefWidth(110);
        entrega = new DatePicker();
        entrega.setPrefWidth(110);
        tablaPedidos = new TableView();
        hora = new Spinner();
        hora.setPrefWidth(50);
        minutos = new Spinner();
        minutos.setPrefWidth(50);
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 18);
        SpinnerValueFactory<Integer> valueFactory2
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        hora.setValueFactory(valueFactory);
        minutos.setValueFactory(valueFactory2);
    }

    private void seccionCentro2() {
        VBox vb = new VBox();
        TableColumn c = new TableColumn<>("Código");
        TableColumn fe = new TableColumn<>("Fecha Pedido");
        TableColumn obs = new TableColumn<>("Observaciones");
        TableColumn fee = new TableColumn<>("Fecha entrega");
        TableColumn hen = new TableColumn<>("Hora entrega");
        TableColumn cli = new TableColumn<>("Cliente");
        TableColumn rem = new TableColumn<>("Vendedor");
        TableColumn tra = new TableColumn<>("Destinatario");
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
        cli.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        propertiesTableView(cli);
        rem.setPrefWidth(100);
        rem.setCellValueFactory(new PropertyValueFactory<>("ci_trabajador"));
        propertiesTableView(rem);

        tra.setPrefWidth(100);
        tra.setCellValueFactory(new PropertyValueFactory<>("destinatario"));
        propertiesTableView(tra);

        cant.setPrefWidth(100);
        cant.setCellValueFactory(new PropertyValueFactory<>("estado"));
        propertiesTableView(cant);

        c.setPrefWidth(100);
        c.setCellValueFactory(new PropertyValueFactory<>("cod_pedido"));
        propertiesTableView(c);
        tablaPedidos.getColumns().addAll(c, fe, fee, hen, cli, rem, tra, cant, obs);
        tablaPedidos.setPrefSize(1000, 600);
        vb.setPadding(new Insets(10, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().add(tablaPedidos);
        root.setCenter(vb);

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
//llama al procedure
                } else {
//llama al procedure
                }
            } else {
                VentanaDialogo.dialogoAdvertencia();
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