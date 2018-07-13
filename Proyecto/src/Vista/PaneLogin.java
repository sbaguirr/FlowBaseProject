package Vista;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Vista.UsuarioSesion;

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
    
    public PaneLogin(){
        root1 = new BorderPane();
        BackgroundFill myBF = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));
        root1.setBackground(new Background(myBF));
        pantallaLogin();
        boton();
    }
    
    private void pantallaLogin(){
        VBox vb = new VBox();
        vb.setPadding(new Insets(30,30,30,30));
        user = new TextField();
        contra = new PasswordField();
        
        vb.getChildren().addAll(user, contra);
        root1.setTop(vb);
    }
    
    private void boton(){
        login = new Button("Login");
        login.setAlignment(Pos.CENTER);
        VBox v = new VBox();
        v.getChildren().add(login);
        root1.setCenter(v);
        login.setOnAction(e->{
            if (UsuarioSesion.Usuarios().contains(new UsuarioSesion(user.getText(), contra.getText()))){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensaje del sistema");
                    alert.setHeaderText("");
                    alert.setContentText("Inicio correcto");
                    alert.showAndWait();
                if(user.getText().equals("Rosa")){
                    /*PaneAdmin p = new PaneAdmin();
                    Scene s = new Scene(p.getRoot1(), 600, 500);
                    Stage st = new Stage();
                    st.setScene(s);
                    st.show();*/
                }else{
                    /*PaneEmpleado p = new PaneEmpleado();
                    Scene s = new Scene(p.getRoot1(), 600, 500);
                    Stage st = new Stage();
                    st.setScene(s);
                    st.show();*/
                }
                    user.setText("");
                    contra.setText("");
               
            }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensaje del sistema");
                    alert.setHeaderText("");
                    alert.setContentText("Inicio de sesion incorrecto");
                    alert.showAndWait();
                    user.setText("");
                    contra.setText("");
                }
        
        });
                }
 
}
