package Vista;


import Main.Proyecto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import controlador.CONSTANTES;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROSA
 */
public class PaneLogin {
    private BorderPane root1;
    private Button login;
    private TextField user;
    private PasswordField contra;

    public BorderPane getRoot1() {
        return root1;
    }

    public PaneLogin() {
        root1 = new BorderPane();
        BackgroundFill myBF = new BackgroundFill(Color.FLORALWHITE, new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));
        root1.setBackground(new Background(myBF));
        inicializarObjetos();
        pantallaLogin();

    }

    private VBox encabezado() {
        VBox k = new VBox();
        HBox h = new HBox();
        Image image = new Image(getClass().getResourceAsStream(CONSTANTES.path_image + "/login.png"));
        Label myLabel = new Label();
        myLabel.setGraphic(new ImageView(image));
        Label titulo = new Label("Sign in");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #8B0086;");
        h.setAlignment(Pos.CENTER);
        //   h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        k.setAlignment(Pos.CENTER);
        k.getChildren().addAll(myLabel, titulo);
        return k;
    }

    private void pantallaLogin() {
        VBox vb = new VBox();
        VBox v2 = new VBox();
        GridPane gp = new GridPane();//
        Label l2 = new Label("Usuario");
        l2.setFont(new Font("Verdana", 12));
        Label l = new Label("Contraseña");
        l.setFont(new Font("Verdana", 12));

        gp.addColumn(0, l2, l);//
        gp.addColumn(1, user, contra);//
        gp.setHgap(10);
        gp.setVgap(10);
        v2.setPadding(new Insets(0, 277, 0, 277));
        v2.setAlignment(Pos.CENTER);
        v2.getChildren().add(gp);//  

        vb.setPadding(new Insets(100, 30, 10, 30));
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(encabezado(), v2, boton());//    
        root1.setTop(vb);
    }

    private void inicializarObjetos() {
        login = new Button("Login");
        login.setAlignment(Pos.CENTER);
        login.setPrefSize(150, 50);
        login.setStyle("-fx-font: 12 Verdana; -fx-base: #9370DB; -fx-text-fill: white;");

        user = new TextField();
        user.setPrefWidth(400);
        user.setPrefHeight(50);
        contra = new PasswordField();
        contra.setPrefWidth(400);
        contra.setPrefHeight(50);
    }

    private VBox boton() {
        VBox v = new VBox();
        v.getChildren().add(login);
        v.setAlignment(Pos.CENTER);
        login.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                validarInicioSesion();
            }
        });
        login.setOnAction(e -> {
            validarInicioSesion();
        });
        return v;
    }

    public void validarInicioSesion(){
        if (UsuarioSesion.Usuarios().contains(new UsuarioSesion(user.getText(), contra.getText()))) {
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje del sistema");
                alert.setHeaderText("");
                alert.setContentText("Inicio correcto");
                alert.showAndWait();*/
                if (user.getText().equalsIgnoreCase("admin")) {
                    PaneMenuPrincipal pn = new PaneMenuPrincipal();
                    PaneMenuPrincipal.nombreUsuario.setText("admin");
                    Proyecto.scene.setRoot(pn.getRoot());
                } else {
                    PaneMenuPrincipalSucursal pn = new PaneMenuPrincipalSucursal();
                    PaneMenuPrincipalSucursal.nombreUsuarioSucursal.setText(user.getText());
                    Proyecto.scene.setRoot(pn.getRoot());
                }
                user.setText("");
                contra.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje del sistema");
                alert.setHeaderText("");
                alert.setContentText("Inicio de sesion incorrecto");
                alert.showAndWait();
                user.setText("");
                contra.setText("");
            }
        }
 
}
