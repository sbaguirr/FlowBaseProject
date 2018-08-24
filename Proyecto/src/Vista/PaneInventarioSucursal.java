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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import modelo.Tb_inventario;

/**
 *
 * @author Tiffy
 */
public class PaneInventarioSucursal {

    private BorderPane root;
    private TableView tablaProductosSucursal;
    private ObservableList<Tb_inventario> listaInventario;
    private DatePicker fecha;
    private Button buscar;
    private Conexion c;

    public PaneInventarioSucursal() {
        root = new BorderPane();
        c = new Conexion();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionTop();
        seccionCentro();
        boton();
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Inventario");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        return h;
    }

    private void inicializarObjetos() {
        fecha = new DatePicker();
        buscar = new Button("Ver");
        tablaProductosSucursal = new TableView();
    }

    private void seccionTop() {
        VBox j = new VBox();
        GridPane p = new GridPane();
        p.addRow(0, fecha, buscar);
        p.setHgap(10);
        j.setPadding(new Insets(20, 0, 10, 50));
        j.setSpacing(5);
        j.getChildren().addAll(encabezado(), p);
        root.setTop(j);
    }

    private void seccionCentro() {
        VBox vb = new VBox();
        tablaProductosSucursal = new TableView();
        TableColumn Tsucursal = new TableColumn<>("Codigo del producto");
        TableColumn Tnombre = new TableColumn<>("Nombre del producto");
        TableColumn Tstock = new TableColumn<>("Stock");
        TableColumn Tcantidad = new TableColumn<>("Cantidad vendida");
        Tsucursal.setPrefWidth(210);
        Tsucursal.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        propertiesTableView(Tsucursal);
        Tnombre.setPrefWidth(355);
        Tnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        propertiesTableView(Tnombre);
        Tstock.setPrefWidth(200);
        Tstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        propertiesTableView(Tstock);
        Tcantidad.setPrefWidth(230);
        Tcantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadVendida"));
        propertiesTableView(Tcantidad);
        tablaProductosSucursal.getColumns().addAll(Tsucursal, Tnombre, Tstock, Tcantidad);
        tablaProductosSucursal.setPrefSize(30, 300);
        vb.setPadding(new Insets(0, 65, 10, 50)); //top, derecha,abajo,izquierda
        vb.getChildren().addAll(tablaProductosSucursal);
        root.setCenter(vb);
    }

    private void cargarTablita() {
        LocalDate f = fecha.getValue();
        if (f != null) {
            c.connect();
            List<Tb_inventario> y = Tb_inventario.callInventario(fecha(f), PaneMenuPrincipalSucursal.nombreUsuarioSucursal.getText(), c.getC());
            c.cerrarConexion();
            listaInventario = FXCollections.observableArrayList(y);
            tablaProductosSucursal.setItems(listaInventario);
        } else {
            VentanaDialogo.dialogoAdvertencia();
        }
    }

    private String fecha(LocalDate f) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(f);
    }

    private void boton() {
        buscar.setOnAction(e -> {
            cargarTablita();
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
            //  PaneMenuPrincipal p = new PaneMenuPrincipal();
            PaneMenuPrincipalSucursal p = new PaneMenuPrincipalSucursal();
            Proyecto.scene.setRoot(p.getRoot());
        });
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);
        root.setBottom(f);
    }
}
