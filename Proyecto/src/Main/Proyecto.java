/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package Main;

import Vista.PaneMenuPrincipal;
import Vista.PaneMenuPrincipalSucursal;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Tiffy
 */
public class Proyecto extends Application {

    public static Scene scene;

    @Override
    public void start(Stage primaryStage) {


         
        
   //    PaneMenuPrincipal p = new PaneMenuPrincipal(); //descomentar si se quiere ver el menu de matriz
      PaneMenuPrincipalSucursal p = new PaneMenuPrincipalSucursal();
        scene = new Scene(new Group(), 1100, 610);
        scene.setRoot(p.getRoot());
        primaryStage.setTitle("Detalles en flores");
        primaryStage.setScene(scene);
        Image image = new Image("/recursos/logo.png");
        primaryStage.getIcons().add(image);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
