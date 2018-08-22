/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package Main;

import Vista.PaneLogin;
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

        PaneLogin p = new PaneLogin();
        scene = new Scene(new Group(), 1100, 610);
        scene.setRoot(p.getRoot1());
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
