/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Tiffy
 */
public class MiniPaneCliente {

    private BorderPane root;
    private TextField cedula, nombres, apellidos, telefono, email, dir, tlf;
    private Button guardar, modificar;
    private ComboBox dia, mes, anio;
    private Stage stageForm;

    public MiniPaneCliente() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionFormulario();
    }

    public Pane getRoot() {
        return root;
    }

    private void seccionFormulario() {
        Label Lcliente = new Label("Cliente");    //
        Label Lcedula = new Label("Cédula");
        Label Lnombre = new Label("Nombres");
        Label Lapellido = new Label("Apellidos");
        Label Ltelefono = new Label("Teléfono");
        Label Lemail = new Label("Email");
        Label Ldireccion = new Label("Dirección");
        Label Ltlf = new Label("Telefono 2");
        Label Lfnacimiento = new Label("Fecha de Nacimiento");

        HBox combo = new HBox();
        combo.getChildren().addAll(dia, mes, anio);

        guardar = new Button("Guardar");
        modificar = new Button("Actualizar");
        HBox hb = new HBox();
        hb.getChildren().addAll(guardar, modificar);

        VBox vb = new VBox();
        GridPane gp = new GridPane();

        gp.addColumn(0, Lcedula, Lnombre, Lapellido, Ltelefono, Ltlf, Lfnacimiento, Ldireccion, Lemail);
        gp.addColumn(1, cedula, nombres, apellidos, telefono, tlf, combo, dir, email);
        gp.add(hb, 1, 9);
        gp.setVgap(10);
        gp.setHgap(10);

        vb.getChildren().addAll(Lcliente, gp);
        vb.setPadding(new Insets(10, 10, 10, 10));
        root.setCenter(vb);

    }

    private void inicializarObjetos() {
        cedula = new TextField();
        cedula.setPrefWidth(100);
        nombres = new TextField();
        nombres.setPrefWidth(250);
        apellidos = new TextField();
        //apellidos.setPrefHeight(50);
        apellidos.setPrefWidth(250);
        telefono = new TextField();
        tlf = new TextField();
        telefono.setPrefWidth(250);
        email = new TextField();
        email.setPrefWidth(250);
        dir = new TextField();
        dir.setPrefHeight(50);
        dir.setPrefWidth(250);

        dia = new ComboBox();
        mes = new ComboBox();
        anio = new ComboBox();

    }

    /**
     * Permite guardar un nuevo cliente
     * Validaciones: que todos los campos esten llenos
     */
    public void guardarCliente() {
        guardar.setOnAction(e -> {

        });
    }
    
    /**
     * Permite actualizar la info del cliente
     * Validaciones: que todos los campos esten llenos
     */
    public void ModificarCliente() {
        guardar.setOnAction(e -> {

        });
    }

    public void showWindow() {
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 400, 390);
        stageForm.setTitle("Cliente");
        stageForm.setScene(scene);
        stageForm.resizableProperty().setValue(Boolean.FALSE);
        stageForm.show();

    }
}
