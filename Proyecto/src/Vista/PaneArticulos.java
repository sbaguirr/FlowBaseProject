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

/**
 *
 * @author Tiffy
 */
public class PaneArticulos {

    private BorderPane root;
    private TextField codigo, descripcion, costo;
    private Button nuevo, guardar, actualizar, eliminar, modificar;
    //private ObservableList<String> listaProductos;
    private TableView tablaProductos;

    public PaneArticulos() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));

        seccionIzquierda();
        Secciontabla();
        nuevo();
        modificar();
        actualizar();
        back();
        //IngresoNumero();

    }

    public Pane getRoot() {
        return root;
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

    private void seccionIzquierda() {
        GridPane grid = new GridPane();
        HBox h = new HBox();
        VBox contenedor = new VBox();
        Label codigoProducto = new Label("Código del Producto");
        Label Ldescripcion = new Label("Descripción");
        Label Lcosto = new Label("Costo");
        codigo = new TextField();
        descripcion = new TextField();
        descripcion.setPrefHeight(100);
        descripcion.setPrefWidth(300);
        costo = new TextField();
        guardar = new Button("Guardar");
        actualizar = new Button("Actualizar");

        desactivarObjetos();
        grid.setVgap(10);
        h.getChildren().addAll(guardar, actualizar);
        grid.addColumn(0, codigoProducto, Ldescripcion, Lcosto);

        grid.addColumn(1, codigo, descripcion, costo, h);
        contenedor.setPadding(new Insets(0, 0, 10, 50)); //top,derecha,abajo,izquierda
        contenedor.getChildren().addAll(encabezado(), grid);
        root.setTop(contenedor);
    }

    private void desactivarObjetos() {
        codigo.setDisable(true);
        descripcion.setDisable(true);
        costo.setDisable(true);
        guardar.setDisable(true);
        actualizar.setDisable(true);

    }

    private void activarObjetos() {
        codigo.setDisable(false);
        descripcion.setDisable(false);
        costo.setDisable(false);
        guardar.setDisable(false);
    }

    private void Secciontabla() {  //corregir
        VBox vb = new VBox();
        HBox hb = new HBox();
        eliminar = new Button("Eliminar");
        modificar = new Button("Modificar");
        nuevo = new Button("Nuevo");
        hb.getChildren().addAll(nuevo, eliminar, modificar);
        tablaProductos = new TableView();
        TableColumn codigoProd = new TableColumn<>("Código");
        TableColumn descrip = new TableColumn<>("Descripción");
        TableColumn cost = new TableColumn<>("Costo");
        codigoProd.setPrefWidth(190);
        //codigoProd.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(codigoProd);
        descrip.setPrefWidth(300);
        //descrip.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(descrip);
        cost.setPrefWidth(200);
        //cost.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(cost);
        tablaProductos.getColumns().addAll(codigoProd, descrip, cost);
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

    /**
     * No estoy muy segura de si hacerlo de esta forma. Al hacer click en una
     * fila, y luego al darle clic al boton, se deberia eliminar el registro
     * Validaciones: seleccionar una fila de la tabla
     */
    private void eliminar() {

    }

    /**
     * No estoy muy segura de si hacerlo de esta forma. Permite actualizar un
     * articulo ingresado. Validaciones: Que todos los campos estén llenos, al
     * menos el codigo
     */
    private void actualizar() {
        actualizar.setOnAction(e -> {
            desactivarObjetos();
        });

    }

    /**
     * No estoy muy segura de si hacerlo de esta forma. Permite ingresar un
     * nuevo articulo Validaciones: Que todos los campos esten llenos
     */
    private void nuevo() {
        nuevo.setOnAction(e -> {
            activarObjetos();
        });

    }

    /**
     * No estoy muy segura de si hacerlo de esta forma. Al seleccionar una fila
     * de la tabla, se deberia colocar la info de lo que se seleccionó en la
     * tabla en los cuadros(?) para que se pueda modificar Validaciones:
     * Seleccionar una fila
     */
    private void modificar() { //
        modificar.setOnAction(e -> {
            actualizar.setDisable(false);
            guardar.setDisable(true);
        });
    }

    private void guardar() {

    }

    /**
     * esto no sirve xd
     */
    private void IngresoNumero() {
        costo.setOnKeyPressed(e -> {
            String c = e.getCharacter();
            if ((c.equals("0") || c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5")
                    || c.equals("6") || c.equals("7") || c.equals("8") || c.equals("9") || c.equals(".") || c.equals(","))) {
                System.out.print("son numeros");
            } else {
                System.out.print("NO son numeros");
            }
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
