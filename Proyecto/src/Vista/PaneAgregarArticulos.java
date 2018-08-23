/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import static controlador.VentanaDialogo.ProductoGuardadoFallido;
import static controlador.VentanaDialogo.noNumerico;
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
import static modelo.Articulo.buscarArticuloXCodigo;
import static modelo.Articulo.ingresarArticulo;
import static modelo.Articulo.llenarArticulos;

/**
 *
 * @author Tiffy
 */
public class PaneAgregarArticulos {

    private BorderPane root;
    private TextField codigo, nombre, descripcion, costo, color;
    private Button guardar;
    private ObservableList<Articulo> listaProductos;
    private TableView tablaProductos;
    private Conexion cone ;
   

    public PaneAgregarArticulos() {
        cone = new Conexion();
        cone.connect();
        listaProductos = FXCollections.observableArrayList();
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        seccionIzquierda();
        Secciontabla();
        guardar();
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Agregar Nuevo Artículo");
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
        codigo = new TextField();
        nombre = new TextField();
        descripcion = new TextField();
        descripcion.setPrefHeight(100);
        descripcion.setPrefWidth(300);
        costo = new TextField();
        color = new TextField();
        guardar = new Button("Guardar");
        grid.setVgap(10);
        grid.setHgap(10);
        h.getChildren().addAll(guardar);
        grid.addColumn(0, codigoProducto, nombreProducto, Ldescripcion, Lcosto, Lcolor);

        grid.addColumn(1, codigo, nombre, descripcion, costo, color, h);
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

    private void guardar() {
        guardar.setOnAction(e->{
            if(validar() && isNumeric(costo.getText())){
                cone.connect();
                String t = buscarArticuloXCodigo(codigo.getText(), cone.getC());
                if (t == null) {
                    ingresarArticulo(codigo.getText(),nombre.getText(), 
                    descripcion.getText(), Float.parseFloat(costo.getText()), 
                    color.getText(), cone.getC());
                    cone.cerrarConexion();
                    
                    limpiarCampos();
                }else{
                    noNumerico();
                    ProductoGuardadoFallido();
                }
            }else{
                ProductoGuardadoFallido();
                noNumerico();
            }
        });
    }

    private void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        descripcion.setText("");
        costo.setText("");
        color.setText("");
    }
    
    private boolean validar() {
        return !codigo.getText().equals("") && !nombre.getText().equals("")
                && !descripcion.getText().equals("") && !costo.getText().equals("")
                && !color.getText().equals("");
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
    
    private boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
}