/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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

/**
 *
 * @author Tiffy
 */
public class PaneMenuPrincipalSucursal {

    private BorderPane root;
    private Button pedidos, verPedidos, cerrarSesion, inventario, verClientes;
    public static Label nombreUsuarioSucursal = new Label();

    public PaneMenuPrincipalSucursal() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        crearSeccionTitulo();
        menu();
        label();
    }

    private void crearSeccionTitulo() {
        HBox hbox = new HBox(); //1. Creo Hbox
        Label titleLbl = new Label("Detalles en Flores"); //2. Creo lbl
        hbox.getChildren().add(titleLbl); //3. Agrego lbl al Hbox
        titleLbl.setFont(new Font("Segoe Print", 50));
        titleLbl.setStyle("-fx-text-fill: #E4C953;");
        //  hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER); //4. setAlignment
        hbox.setPadding(new Insets(50, 10, 0, 10));
        root.setTop(hbox);
    }

    private void menu() {
        VBox j = new VBox();
        HBox h1 = new HBox();
        h1.getChildren().addAll(botonPedidos(), verPedidos(), verClientes());
        h1.setSpacing(14);
        h1.setAlignment(Pos.CENTER);
        HBox h2 = new HBox();
        h2.setSpacing(14);
        h2.getChildren().addAll(inventarioS(), cerrarSesion());
        h2.setAlignment(Pos.CENTER);
        j.setSpacing(14);
        j.getChildren().addAll(h1, h2);
        j.setAlignment(Pos.CENTER);
        j.setPadding(new Insets(0, 0, 50, 50)); //top, derecha,abajo,izquierda
        root.setCenter(j);

    }

    private Button botonPedidos() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/nuevo.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        pedidos = new Button("Agregar Pedidos");
        pedidos.setPrefSize(250, 150);
        pedidos.setStyle("-fx-font: 16 Verdana; -fx-base: #DB7093; -fx-text-fill: white;");
        pedidos.setContentDisplay(ContentDisplay.TOP);
        pedidos.setGraphic(w);
        pedidos.setOnAction(e -> {
            PaneIngresarPedidos p = new PaneIngresarPedidos();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return pedidos;
    }

    private Button verPedidos() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/eyeglasses.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        verPedidos = new Button("Ver Pedidos", w);
        verPedidos.setPrefSize(250, 150);
        verPedidos.setStyle("-fx-font: 16 Verdana; -fx-base: #ADFF2F; -fx-text-fill: white;"); //VERDE
        verPedidos.setContentDisplay(ContentDisplay.TOP);
        verPedidos.setGraphic(w);
        verPedidos.setOnAction(e -> {
            PaneVerPedidos p = new PaneVerPedidos();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return verPedidos;
    }

    private Button cerrarSesion() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/exit.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        cerrarSesion = new Button("Cerrar Sesión", w);
        cerrarSesion.setPrefSize(250, 150);
        cerrarSesion.setStyle("-fx-font: 16 Verdana; -fx-base: #FF69B4; -fx-text-fill: white;");
        cerrarSesion.setContentDisplay(ContentDisplay.TOP);
        cerrarSesion.setGraphic(w);
        cerrarSesion.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir del sistema");
            alert.setHeaderText("¿Está seguro que desea Cerrar Sesión?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                PaneLogin p = new PaneLogin();
                Proyecto.scene.setRoot(p.getRoot1());
            }
        });
        return cerrarSesion;
    }

    private Button inventarioS() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/inventario.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        inventario = new Button("Inventario", w);
        inventario.setPrefSize(250, 150);
        inventario.setStyle("-fx-font: 16 Verdana; -fx-base: #00CED1; -fx-text-fill: white;");
        inventario.setContentDisplay(ContentDisplay.TOP);
        inventario.setGraphic(w);
        inventario.setOnAction(e -> {
            PaneInventarioSucursal p = new PaneInventarioSucursal();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return inventario;
    }

    private Button verClientes() {
        Image imagePlay = new Image(getClass().getResource(CONSTANTES.path_image + "/users.png").toExternalForm());
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        verClientes = new Button("Ver clientes", w);
        verClientes.setPrefSize(250, 150);
        verClientes.setStyle("-fx-font: 16 Verdana; -fx-base: #B0C4DE; -fx-text-fill: white;");
        verClientes.setContentDisplay(ContentDisplay.TOP);
        verClientes.setGraphic(w);
        verClientes.setOnAction(e -> {
            PaneVerClientes p = new PaneVerClientes();
            Proyecto.scene.setRoot(p.getRoot());
        });
        return verClientes;

    }

    public Pane getRoot() {
        return root;
    }

    private void label() {
        nombreUsuarioSucursal.setStyle("-fx-font: 12 Verdana;");
        HBox b = new HBox();
        b.setAlignment(Pos.BOTTOM_CENTER);
        Label user = new Label("Sesión actual: ");
        user.setStyle("-fx-font: 12 Verdana;");
        b.getChildren().addAll(user, nombreUsuarioSucursal);
        root.setBottom(b);
    }
}
