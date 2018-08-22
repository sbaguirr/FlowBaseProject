/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import static controlador.VentanaDialogo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import modelo.Articulo;
import static modelo.Articulo.buscarArticulo;
import static modelo.Articulo.llenarArticulos;
import static modelo.Articulo.modificarArticulo;

/**
 *
 * @author Tiffy
 */
public class PaneModificarArticulos {

    private BorderPane root;
    public static TextField txt_codigo, txt_nombre, txt_descripcion, txt_costo, txt_color;
    private Button modificar, btnBuscar;
    private ObservableList<Articulo> listaProductos;
    private TableView tablaProductos;
    private Conexion cone ;
   

    public PaneModificarArticulos() {
        cone = new Conexion();
        cone.connect();
        listaProductos = FXCollections.observableArrayList();
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        seccionIzquierda();
        Secciontabla();
        modificar();
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Modificar Artículo");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        return h;
    }

    private void seccionIzquierda() {
        GridPane grid = new GridPane();
        HBox h = new HBox();
        VBox contenedor = new VBox();
        Label codigoProducto = new Label("Código del Producto");
        Label nombreProducto = new Label("Nombre");
        Label Ldescripcion = new Label("Descripción");
        Label Lcosto = new Label("Costo");
        Label Lcolor = new Label("Color");
        txt_codigo = new TextField();
        txt_nombre = new TextField();
        txt_descripcion = new TextField();
        txt_descripcion.setPrefHeight(100);
        txt_descripcion.setPrefWidth(300);
        txt_costo = new TextField();
        txt_color = new TextField();
        modificar = new Button("Modificar");
        btnBuscar = new Button("Buscar");
        desactivarObjetos();
        btnBuscar.setOnAction(e->{
            if(!txt_codigo.getText().equals("")){
                cone.connect();
                activarObjetos();
                buscarArticulo(txt_codigo.getText(), cone.getC());
            }else{
                VentanaRegistroNoEncontrado();
                desactivarObjetos();
                limpiarCampos();
            }
        });
        grid.setVgap(10);
        grid.setHgap(10);
        h.getChildren().addAll(modificar);
        grid.addColumn(0, codigoProducto, nombreProducto, Ldescripcion, Lcosto, Lcolor);
        grid.addColumn(1, txt_codigo, txt_nombre, txt_descripcion, txt_costo, txt_color, h);
        grid.addColumn(2, btnBuscar);
        contenedor.setPadding(new Insets(0, 0, 10, 50)); //top,derecha,abajo,izquierda
        contenedor.getChildren().addAll(encabezado(), grid);
        root.setTop(contenedor);
    }
    
    private void Secciontabla() {  
        VBox vb = new VBox();
        HBox hb = new HBox();
        tablaProductos = new TableView();
        
        llenarArticulos(cone.getC(),listaProductos);
        TableColumn<Articulo, String> codigoProd = new TableColumn<>("Código");
        TableColumn<Articulo, String> nombre = new TableColumn<>("Nombre");
        TableColumn<Articulo, String> descrip = new TableColumn<>("Descripción");
        TableColumn cost = new TableColumn<>("Costo");
        TableColumn color = new TableColumn<>("Color");
        codigoProd.setPrefWidth(190);
        
        codigoProd.setCellValueFactory(new PropertyValueFactory<>("cod_articulo"));
        propertiesTableView(codigoProd);
        descrip.setPrefWidth(300);
        
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        propertiesTableView(descrip);
        propertiesTableView(nombre);
        cost.setPrefWidth(200);
        
        cost.setCellValueFactory(new PropertyValueFactory<>("costo"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        propertiesTableView(cost);
        propertiesTableView(color);
        tablaProductos.setItems(listaProductos);
        tablaProductos.getColumns().addAll(codigoProd, nombre, descrip, cost, color);
        tablaProductos.setPrefSize(40, 300);
        vb.setPadding(new Insets(0, 25, 10, 50)); //top, derecha,abajo,izquierda
        vb.getChildren().addAll(tablaProductos, hb);
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

    private void modificar() {
        modificar.setOnAction(e->{
            if(validar()){
                cone.connect();
                modificarArticulo(txt_codigo.getText(), txt_nombre.getText(), 
                txt_descripcion.getText(), Float.parseFloat(txt_costo.getText()), 
                txt_color.getText(), cone.getC());
                cone.cerrarConexion();
                ProductoModificadoExitosamente();
                limpiarCampos();
            }else{
                ProductoModificadoFallido(); 
                limpiarCampos();
            }
        });
    }
    
    private void desactivarObjetos() {
        txt_nombre.setDisable(true);
        txt_descripcion.setDisable(true);
        txt_costo.setDisable(true);
        txt_color.setDisable(true);
        modificar.setDisable(true);
    }

    private void activarObjetos() {
        modificar.setDisable(false);
        txt_nombre.setDisable(false);
        txt_descripcion.setDisable(false);
        txt_costo.setDisable(false);
        txt_color.setDisable(false);
    }

    public static void limpiarCampos() {
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_descripcion.setText("");
        txt_costo.setText("");
        txt_color.setText("");
    }
    
    private boolean validar() {
        return !txt_codigo.getText().equals("") && !txt_nombre.getText().equals("")
                && !txt_descripcion.getText().equals("") && !txt_costo.getText().equals("")
                && !txt_color.getText().equals("");
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
