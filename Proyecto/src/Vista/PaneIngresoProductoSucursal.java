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
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
import modelo.Tb_envio_articulo;

/**
 *
 * @author Tiffy
 */
public class PaneIngresoProductoSucursal {

    private BorderPane root;
    private ComboBox<String> sucursal;
    public static TextField codigo;
    private TextField cantidad;
    private Button showMore, agregar, eliminar, actualizar;
    private Conexion c;
    private ObservableList<Tb_envio_articulo> listaProductos;
    private TableView<Tb_envio_articulo> tablaProductosSucursal;
    public static int indicador;

    public PaneIngresoProductoSucursal() {
        root = new BorderPane();
        c = new Conexion();
        indicador = 1;
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        seccionIzquierda();
        seccionCentro();
        llamarMetodos();

    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Ingreso Productos Sucursal");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        return h;
    }

    private void llamarMetodos() {
        cargarCombo();
        Vercodigos();
        cargarContenido();
        agregar();
        disminuir();
        f5();
        back();
    }

    private void seccionIzquierda() {
        GridPane gp = new GridPane();
        VBox vb = new VBox();

        Label Lsucursal = new Label("Seleccione sucursal");
        Label Lproducto = new Label("Seleccione producto");
        Label Lcantidad = new Label("cantidad");
        sucursal = new ComboBox();
        sucursal.setPrefWidth(200);
        cantidad = new TextField();
        codigo = new TextField();
        actualizar = new Button("Actualizar");

        showMore = new Button("Seleccionar");
        agregar = new Button("Agregar al stock");
        eliminar = new Button("Disminuir al stock");//validar cantidades +
        HBox g = new HBox();
        g.getChildren().addAll(agregar, eliminar);
        gp.addColumn(0, Lsucursal, Lproducto, Lcantidad);
        gp.addColumn(1, sucursal, codigo, cantidad, g);
        gp.add(showMore, 2, 1);

        gp.setVgap(10);
        gp.setHgap(15);
        vb.setPadding(new Insets(0, 0, 10, 50));
        vb.setSpacing(5);
        vb.getChildren().addAll(encabezado(), gp);

        root.setTop(vb);
    }

    private void seccionCentro() {
        VBox vb = new VBox();

        tablaProductosSucursal = new TableView();
        TableColumn cod = new TableColumn<>("n°");
        TableColumn Tsucursal = new TableColumn<>("Sucursal");
        TableColumn Tcodigo = new TableColumn<>("Codigo del producto");
        TableColumn Tfecha = new TableColumn<>("Stock");

        cod.setPrefWidth(180);
        cod.setCellValueFactory(new PropertyValueFactory<>("id_envio_articulo"));
        propertiesTableView(cod);

        Tsucursal.setPrefWidth(190);
        Tsucursal.setCellValueFactory(new PropertyValueFactory<>("id_floreria"));
        propertiesTableView(Tsucursal);

        Tcodigo.setPrefWidth(300);
        Tcodigo.setCellValueFactory(new PropertyValueFactory<>("cod_articulo"));
        propertiesTableView(Tcodigo);

        Tfecha.setPrefWidth(190);
        Tfecha.setCellValueFactory(new PropertyValueFactory<>("stock"));
        propertiesTableView(Tfecha);

        tablaProductosSucursal.getColumns().addAll(cod, Tsucursal, Tcodigo, Tfecha);
        tablaProductosSucursal.setPrefSize(40, 300);
        vb.setPadding(new Insets(0, 25, 10, 50)); //top, derecha,abajo,izquierda

        vb.getChildren().addAll(tablaProductosSucursal, actualizar);
        root.setCenter(vb);
    }

    private void cargarContenido() {
        listaProductos = FXCollections.observableArrayList();
        c.connect();
        Tb_envio_articulo.llenarEnvios(listaProductos, c.getC());
        c.cerrarConexion();
        tablaProductosSucursal.setItems(listaProductos);
    }

    /**
     * De acuerdo a lo seleccionado en el combo, se aplicarán los filtros
     */
    private void cargarCombo() {
        String se[] = {"Cdla. Kennedy Nueva", "Mall del Sol", "C.C. El Dorado"};
        sucursal.setItems(FXCollections.observableArrayList(se));
    }

    /**
     * Método que al seleccionar un comboBox devuelve un String
     *
     * @return g, String
     */
    private String seleccion() {
        return sucursal.getSelectionModel().getSelectedItem();
    }

    private void agregar() {
        agregar.setOnAction(e -> {
            if (validar()) {
                c.connect();
                int t = Tb_envio_articulo.callModificarStockSucursal(Integer.valueOf(cantidad.getText()), seleccion(), 0, codigo.getText().toUpperCase(), c.getC());
                if (t > 0) {
                    if (!seleccion().equals("Cdla. Kennedy Nueva")) {
                        int t1 = Tb_envio_articulo.callDescuentoStockMatriz(Integer.valueOf(cantidad.getText()), codigo.getText(), c.getC());
                        if (t1 < 0) {
                            VentanaDialogo.dialogoAdvertencia4();
                        }
                    }
                    VentanaDialogo.dialogoAccion();
                } else {
                    VentanaDialogo.dialogoAdvertencia3();

                }
                c.cerrarConexion();
            } else {
                VentanaDialogo.dialogoAdvertencia();
            }
        });
    }

    private void f5() {
        actualizar.setOnAction(e -> {
            cargarContenido();
        });

    }

    private void disminuir() {
        eliminar.setOnAction(e -> {
            if (validar()) {
                c.connect();
                int t = Tb_envio_articulo.callModificarStockSucursal(Integer.valueOf(cantidad.getText()), seleccion(), 1, codigo.getText().toUpperCase(), c.getC());
                if (t > 0) {
                    if (!seleccion().equals("Cdla. Kennedy Nueva")) {
                        Tb_envio_articulo.callRestaurarStockMatriz(Integer.valueOf(cantidad.getText()), codigo.getText(), c.getC());
                    }
                    VentanaDialogo.dialogoAccion();
                } else {
                    VentanaDialogo.dialogoAdvertencia3();
                }
                c.cerrarConexion();
            } else {
                VentanaDialogo.dialogoAdvertencia();
            }
        });
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

    private boolean validar() {
        return seleccion() != null && cantidad.getText() != null && codigo.getText() != null && Integer.valueOf(cantidad.getText()) > 0;
    }

    public Pane getRoot() {
        return root;
    }

    private void Vercodigos() {
        showMore.setOnAction(e -> {
            MiniPaneArticulo mpp = new MiniPaneArticulo();
            mpp.showWindow();
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
