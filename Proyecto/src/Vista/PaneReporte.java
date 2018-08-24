/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import modelo.Tb_cliente;
import modelo.Tb_reporteVentaVendedor;

/**
 *
 * @author Tiffy
 */
public class PaneReporte {

    private BorderPane root;
    private TableView tablaPedidos;
    private TableView tablaPedidosHistorico;
    private TableView tablaCliente;
    private ObservableList<Tb_reporteVentaVendedor> pedidos;
    private ObservableList<Tb_reporteVentaVendedor> pedidosHistorico;
    private ObservableList<Tb_cliente> clientes;
    private Conexion c;

    public PaneReporte() {
        root = new BorderPane();
        c = new Conexion();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        llamarMetodos();

    }

    public Pane getRoot() {
        return root;

    }

    private void llamarMetodos() {
        encabezado();
        unir();
        cargarTabla1();
        cargarTabla2();
        cargarTabla3();
        back();

    }

    private void encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Reporte");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 8, 5, 8));
        h.getChildren().add(titulo);
        root.setTop(h);
    }

    private VBox tabla1() {
        VBox vb = new VBox();
        tablaPedidos = new TableView();
        Label f = new Label("Ventas por vendedor del mes actual");
        f.setFont(new Font("Verdana", 14));
        f.setPadding(new Insets(5, 3, 5, 25));
        TableColumn fe = new TableColumn<>("Nombres");
        TableColumn obs = new TableColumn<>("Apellidos");
        TableColumn fee = new TableColumn<>("Total ventas");
        fe.setPrefWidth(120);
        fe.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        propertiesTableView(fe);
        obs.setPrefWidth(120);
        obs.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        propertiesTableView(obs);
        fee.setPrefWidth(80);
        fee.setCellValueFactory(new PropertyValueFactory<>("venta"));
        propertiesTableView(fee);
        tablaPedidos.getColumns().addAll(fe, obs, fee);
        tablaPedidos.setPrefSize(390, 500);
        vb.setPadding(new Insets(5, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().addAll(f, tablaPedidos);
        return vb;
    }

    //String id, String nombres, String apellidos, float costo
    private VBox tabla2() {
        VBox vb = new VBox();
        tablaPedidosHistorico = new TableView();
        Label f = new Label("Ventas totales por vendedor");
        f.setPadding(new Insets(5, 3, 5, 40));
        f.setFont(new Font("Verdana", 14));
        TableColumn fe = new TableColumn<>("Nombres");
        TableColumn obs = new TableColumn<>("Apellidos");
        TableColumn fee = new TableColumn<>("Total ventas");
        fe.setPrefWidth(120);
        fe.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        propertiesTableView(fe);
        obs.setPrefWidth(120);
        obs.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        propertiesTableView(obs);
        fee.setPrefWidth(80);
        fee.setCellValueFactory(new PropertyValueFactory<>("venta"));
        propertiesTableView(fee);
        tablaPedidosHistorico.getColumns().addAll(fe, obs, fee);
        tablaPedidosHistorico.setPrefSize(390, 500);
        vb.setPadding(new Insets(5, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().addAll(f, tablaPedidosHistorico);
        return vb;
    }

    private VBox tabla3() {
        VBox vb = new VBox();
        tablaCliente = new TableView();
        Label f = new Label("Mejores clientes");
        f.setPadding(new Insets(5, 5, 5, 119));
        f.setFont(new Font("Verdana", 14));
        TableColumn fe = new TableColumn<>("Nombres");
        TableColumn obs = new TableColumn<>("Apellidos");
        TableColumn fee = new TableColumn<>("Número de Compras");
        fe.setPrefWidth(120);
        fe.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        propertiesTableView(fe);
        obs.setPrefWidth(120);
        obs.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        propertiesTableView(obs);
        fee.setPrefWidth(150);
        fee.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        propertiesTableView(fee);
        tablaCliente.getColumns().addAll(fe, obs, fee);
        tablaCliente.setPrefSize(450, 500);
        vb.setPadding(new Insets(5, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().addAll(f, tablaCliente);
        return vb;
    }

    private void unir() {
        HBox hb = new HBox();
        hb.getChildren().addAll(tabla1(), tabla2(), tabla3());
        hb.setSpacing(10);
        hb.setPadding(new Insets(0, 35, 0, 35));
        root.setCenter(hb);
    }

    private void cargarTabla1() {
        try {
            c.connect();
            List<Tb_reporteVentaVendedor> t = Tb_reporteVentaVendedor.ejecutarVista2(c.getC());
            pedidos = FXCollections.observableArrayList(t);
            tablaPedidos.setItems(pedidos);
            c.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void cargarTabla2() {
        try {
            c.connect();
            List<Tb_reporteVentaVendedor> t = Tb_reporteVentaVendedor.ejecutarVista1(c.getC());
            pedidosHistorico = FXCollections.observableArrayList(t);
            tablaPedidosHistorico.setItems(pedidosHistorico);
            c.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void cargarTabla3() {
        try {
            c.connect();
            List<Tb_cliente> t = Tb_cliente.reporteMejoresClientes(c.getC());
            clientes = FXCollections.observableArrayList(t);
            tablaCliente.setItems(clientes);
            c.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }

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


