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
import static modelo.Articulo.buscarArticuloXCodigo;
import static modelo.Articulo.eliminarArticulo;
import static modelo.Articulo.llenarArticulos;

/**
 *
 * @author Tiffy
 */
public class PaneEliminarArticulos {

    private BorderPane root;
    private TextField codigo;
    private Button eliminar;
    private ObservableList<Articulo> listaProductos;
    private TableView tablaProductos;
    private Conexion cone ;
   

    public PaneEliminarArticulos() {
        cone = new Conexion();
        cone.connect();
        listaProductos = FXCollections.observableArrayList();
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        seccionIzquierda();
        Secciontabla();
        eliminar();
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Eliminar Artículo");
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
        codigo = new TextField();
        eliminar = new Button("Eliminar");
        grid.setVgap(10);
        grid.setHgap(10);
        h.getChildren().addAll(eliminar);
        grid.addColumn(0, codigoProducto);

        grid.addColumn(1, codigo, h);
        contenedor.setPadding(new Insets(0, 0, 10, 50)); //top,derecha,abajo,izquierda
        contenedor.getChildren().addAll(encabezado(), grid);
        root.setTop(contenedor);
    }
    
    private void Secciontabla() {  
        VBox vb = new VBox();
        HBox hb = new HBox();
        tablaProductos = new TableView();
        
        llenarArticulos(cone.getC(),listaProductos);
        TableColumn codigoProd = new TableColumn<>("Código");
        TableColumn nombreP = new TableColumn<>("Nombre");
        TableColumn descrip = new TableColumn<>("Descripción");
        TableColumn cost = new TableColumn<>("Costo");
        TableColumn colorP = new TableColumn<>("Color");
        codigoProd.setPrefWidth(190);
        
        codigoProd.setCellValueFactory(new PropertyValueFactory<>("cod_articulo"));
        propertiesTableView(codigoProd);
        descrip.setPrefWidth(300);
        
        nombreP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        propertiesTableView(descrip);
        propertiesTableView(nombreP);
        cost.setPrefWidth(200);
        
        cost.setCellValueFactory(new PropertyValueFactory<>("costo"));
        colorP.setCellValueFactory(new PropertyValueFactory<>("color"));
        propertiesTableView(cost);
        propertiesTableView(colorP);
        tablaProductos.setItems(listaProductos);
        tablaProductos.getColumns().addAll(codigoProd, nombreP, descrip, cost, colorP);
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

    private void eliminar() {
        eliminar.setOnAction(e->{
            if(validar()){
                cone.connect();
                String t = buscarArticuloXCodigo(codigo.getText(), cone.getC());
                if (t == null) {
                    eliminarArticulo(codigo.getText(), cone.getC());
                    cone.cerrarConexion();
                    ProductoEliminadoExitosamente();
                    limpiarCampos();
                }else{
                    ProductoEliminadoFallido();
                }
            }else{
                CampoVacio(); 
            }
        });
    }

    private void limpiarCampos() {
        codigo.setText("");
    }
    
    private boolean validar(){
        return (!codigo.getText().equals(""));
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
