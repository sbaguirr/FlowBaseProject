/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class PaneInventarioSucursal {

    private BorderPane root;
    private TableView tablaProductosSucursal;
    private ComboBox sucursales, producto, mes, anio;
    private TextField cantidad;
    private Button buscar, aplicar;
    private RadioButton si, no;

    public PaneInventarioSucursal() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionTop();
        seccionCentro();
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private void inicializarObjetos() {
        sucursales = new ComboBox();
        sucursales.setPrefSize(150, 25);
        producto = new ComboBox();
        producto.setDisable(true);
        producto.setPrefSize(150, 25);
        mes = new ComboBox();
        
        mes.setPrefSize(75, 25);
        anio = new ComboBox();
        anio.setPrefSize(70, 25);

        buscar = new Button("Buscar");
        aplicar = new Button("Aplicar");
        aplicar.setDisable(true);

        si = new RadioButton("Si    ");
        no = new RadioButton("No");
        tablaProductosSucursal = new TableView();

        cantidad = new TextField();
        cantidad.setDisable(true);

    }

    private void seccionTop() {
        VBox j = new VBox();
        Label l = new Label("Seleccione Sucursal");
        Label f = new Label("fecha");
        Label flit = new Label("Filtros");
        Label cant = new Label("Cantidad");
        Label produc = new Label("Producto");
        GridPane p = new GridPane();
        HBox hb = new HBox();
        HBox hb2 = new HBox();
        hb.getChildren().addAll(si, no);
        hb2.getChildren().addAll(mes, anio);
        hb.setSpacing(10);
        p.addColumn(0, l, flit);
        p.addColumn(1, sucursales, hb);
        p.add(f, 2, 0);
        p.add(hb2, 3, 0);
        p.add(buscar, 4, 0);
        p.setVgap(10);
        p.setHgap(10);
        p.add(produc, 0, 3);
        p.add(producto, 1, 3);
        p.add(cant, 2, 3);
        p.add(cantidad, 3, 3);
        p.add(aplicar, 4, 3);
        j.setPadding(new Insets(20, 0, 10, 50));
        j.getChildren().add(p);
        root.setTop(j);
    }

    /**
     * No se que mas deberia incluirse aqui
     */
    private void seccionCentro() {
        VBox vb = new VBox();
         tablaProductosSucursal = new TableView();
        TableColumn Tsucursal = new TableColumn<>("Sucursal");
        TableColumn Tcodigo = new TableColumn<>("Codigo del producto");
        TableColumn Tcantidad = new TableColumn<>("Cantidad");
        Tsucursal.setPrefWidth(190);
        //Tsucursal.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(Tsucursal);
        Tcodigo.setPrefWidth(300);
        //Tcodigo.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(Tcodigo);
        Tcantidad.setPrefWidth(200);
        //Tcantidad.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(Tcantidad);
        tablaProductosSucursal.getColumns().addAll(Tsucursal,Tcodigo, Tcantidad);
        tablaProductosSucursal.setPrefSize(40, 300);
        vb.setPadding(new Insets(0, 25, 10, 50)); //top, derecha,abajo,izquierda
        vb.getChildren().addAll(tablaProductosSucursal);
        root.setCenter(vb);
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
            PaneMenuPrincipal p = new PaneMenuPrincipal();
             Proyecto.scene.setRoot(p.getRoot());
        });
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);
        root.setBottom(f);
    }
}
