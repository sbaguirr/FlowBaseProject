/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import controlador.VentanaDialogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Articulo;

/**
 *
 * @author Tiffy
 */
public class MiniPaneArticulo {

    private BorderPane root;
    private TableView<Articulo> articulos;
    private ObservableList<Articulo> l_articulos;
    private FilteredList<Articulo> filtro;
    private Button seleccionar;
    private TextField buscar;
    private Stage stageForm;
    private Conexion c;

    public MiniPaneArticulo() {
        root = new BorderPane();
        c = new Conexion();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjtos();
        tablita();
        cargarContenido();
        cuadro();
        seleccionarArticulo();
    }

    private void inicializarObjtos() {
        articulos = new TableView();
        seleccionar = new Button("Seleccionar");
        buscar = new TextField();
        buscar.setPromptText("Buscar...");

    }

    private Pane getRoot() {
        return root;
    }

    private void cuadro() {
        buscar.textProperty().addListener((prop, old, text) -> {
            filtro.setPredicate(articulo -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }

                String cod = articulo.getCod_articulo();
                return cod.contains(text);
            });
        });

    }

    private TableView tablaArticulo() {
        VBox h = new VBox();
        articulos.setEditable(false);
        TableColumn codigo = new TableColumn<>("Codigo");
        TableColumn descripcion = new TableColumn<>("Nombre");
        codigo.setPrefWidth(100);
        codigo.setCellValueFactory(new PropertyValueFactory<>("cod_articulo"));
        propertiesTableView(codigo);
        descripcion.setPrefWidth(180);
        descripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        propertiesTableView(descripcion);
        articulos.setPrefSize(25, 300);
        articulos.getColumns().addAll(codigo, descripcion);
        h.getChildren().add(articulos);
        h.setPadding(new Insets(30, 200, 50, 198));
        return articulos;
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

    private void tablita() {
        VBox daomi = new VBox();
        daomi.getChildren().addAll(buscar, tablaArticulo(), seleccionar);
        daomi.setPadding(new Insets(10 , 10, 10, 10));
        root.setTop(daomi);
    }

    /**
     * Método que cargará la información de los paises en el TableView
     */
    private void cargarContenido() {
        l_articulos = FXCollections.observableArrayList();
        c.connect();
        Articulo.llenarArticulos(c.getC(), l_articulos);
        c.cerrarConexion();
        filtro = new FilteredList<>(l_articulos, p -> true);
        SortedList<Articulo> sortedData = new SortedList<>(filtro);
        articulos.setItems(sortedData);
    }

     /**
     * Método que permitirá obtener un objeto Pais al hacer click sobre una de
     * las filas del TableView
     */
    private void seleccionarArticulo() {

        seleccionar.setOnAction((e) -> {
            try {
                Articulo w = articulos.getSelectionModel().getSelectedItem();
                if(w!=null){
                if (PaneIngresarPedidos.indicador == 1) {
                    PaneIngresarPedidos.codigo.setText(w.getCod_articulo());    
                }  if (PaneIngresoProductoSucursal.indicador == 1 ) {
                    PaneIngresoProductoSucursal.codigo.setText(w.getCod_articulo());
                }
                }else{
                VentanaDialogo.dialogoAdvertencia2();
                }
                stageForm.close();
            } catch (Exception ex) {
                VentanaDialogo.dialogoAdvertencia2();
            }
        });
        
    }
    /**
     * Método que permitirá llamar a este Pane desde otra clase
     */
    public void showWindow() {
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 300, 370);
        stageForm.setTitle("Articulos");
        stageForm.setScene(scene);
        Image image = new Image("/recursos/logo.png");
        stageForm.getIcons().add(image);
        stageForm.resizableProperty().setValue(Boolean.FALSE);
        stageForm.show();

    }

}

