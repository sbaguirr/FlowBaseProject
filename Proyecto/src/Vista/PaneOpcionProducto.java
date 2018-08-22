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

/**
 *
 * @author CltControl
 */
public class PaneOpcionProducto {

    private BorderPane root;
    private Button ingresar, modificar, eliminar;

    public PaneOpcionProducto() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarInicializar();
        menu();
        back();
    }

    private void inicializarInicializar() {
        ingresar = new Button();
        modificar = new Button();
        eliminar = new Button();
    }
    public Pane getRoot() {
        return root;
    }

    private void menu() {
        VBox j = new VBox();
        HBox h1 = new HBox();
        h1.getChildren().addAll(botonIngresar(), botonModificar(), botonEliminar());
        h1.setSpacing(14);
        h1.setAlignment(Pos.CENTER);
        j.setSpacing(14);
        j.getChildren().addAll(h1);
        j.setAlignment(Pos.CENTER);
        j.setPadding(new Insets(0, 0, 50, 50)); //top, derecha,abajo,izquierda
        root.setCenter(j);

    }

    private Button botonIngresar() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/add.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        ingresar = new Button("Agregar Productos");
        ingresar.setPrefSize(250, 150);
        ingresar.setStyle("-fx-font: 16 Verdana; -fx-base: #DB7093; -fx-text-fill: white;");
        ingresar.setContentDisplay(ContentDisplay.TOP);
        ingresar.setGraphic(w);
        ingresar.setOnAction(e -> {
            PaneAgregarArticulos p = new PaneAgregarArticulos();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return ingresar;
    }

    private Button botonModificar() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/modify.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        modificar = new Button("Modificar Productos");
        modificar.setPrefSize(250, 150);
        modificar.setStyle("-fx-font: 16 Verdana; -fx-base: #00CED1; -fx-text-fill: white;");
        modificar.setContentDisplay(ContentDisplay.TOP);
        modificar.setGraphic(w);
        modificar.setOnAction(e -> {
            PaneModificarArticulos p = new PaneModificarArticulos();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return modificar;
    }

    private Button botonEliminar() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/drop.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        eliminar = new Button("Eliminar Productos");
        eliminar.setPrefSize(250, 150);
        eliminar.setStyle("-fx-font: 16 Verdana; -fx-base: #B0C4DE; -fx-text-fill: white;");
        eliminar.setContentDisplay(ContentDisplay.TOP);
        eliminar.setGraphic(w);
        eliminar.setOnAction(e -> {
            PaneEliminarArticulos p = new PaneEliminarArticulos();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return eliminar;
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
