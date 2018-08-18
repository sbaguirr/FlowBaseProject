/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import controlador.VentanaDialogo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import javafx.stage.Stage;
import modelo.Tb_cliente;

/**
 *
 * @author Tiffy
 */
public class MiniPaneCliente {

    private BorderPane root;
    public static TextField cedula, nombres, apellidos, telefono, email, dir, tlf, ruc, oNombre, oDir, oTlf;
    public static Button guardar, modificar;
    private Stage stageForm;
    private static String tl1, tl2;
    private Conexion co = new Conexion();

    public MiniPaneCliente() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.LINEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        llamarMetodos();
    }

    public Pane getRoot() {
        return root;
    }

    private void llamarMetodos() {
        inicializarObjetos();
        seccionFormulario();
        guardarCliente();
        ModificarCliente();
    }

    private void seccionFormulario() {
        Label Lcliente = new Label("Cliente");    //
        Lcliente.setFont(new Font("Verdana", 12));
        Lcliente.setStyle("-fx-text-fill: #E4C953;");
        Label Lcedula = new Label("Cédula*");
        Label Lnombre = new Label("Nombres*");
        Label Lapellido = new Label("Apellidos*");
        Label Ltelefono = new Label("Teléfono*");
        Label Lemail = new Label("Email");
        Label Ldireccion = new Label("Dirección*");
        Label Ltlf = new Label("Telefono 2");
        Label oRuc = new Label("RUC");
        Label onom = new Label("Nombre");
        Label odir = new Label("Dirección");
        Label otlf = new Label("Teléfono");
        Label other = new Label("Facturación");
        other.setFont(new Font("Verdana", 12));
        other.setStyle("-fx-text-fill: #E4C953;");

        guardar = new Button("Guardar");
        modificar = new Button("Actualizar");
        HBox hb = new HBox();
        hb.getChildren().addAll(guardar, modificar);

        VBox vb = new VBox();
        GridPane gp = new GridPane();

        gp.addColumn(0, Lcedula, Lnombre, Lapellido, Ltelefono, Ltlf, Ldireccion, Lemail, other, oRuc, onom, odir, otlf);
        gp.addColumn(1, cedula, nombres, apellidos, telefono, tlf, dir, email);
        gp.add(ruc, 1, 8);
        gp.add(oNombre, 1, 9);
        gp.add(oDir, 1, 10);
        gp.add(oTlf, 1, 11);
        gp.add(hb, 1, 13);
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
        ruc = new TextField();
        oNombre = new TextField();
        oDir = new TextField();
        oTlf = new TextField();

    }

    /**
     * Permite guardar un nuevo cliente Validaciones: que el campo ci no esté
     * vacio
     */
    private void guardarCliente() {
        guardar.setOnAction(e -> {
            if (validar()) {
                co.connect();
                Tb_cliente f = Tb_cliente.buscarCliente(cedula.getText(), co.getC());
                if (f.getCi_cliente() == null) {
                    Tb_cliente.ingresarDatosCliente(cedula.getText(), nombres.getText(), apellidos.getText(), dir.getText(), email.getText(), co.getC());
                    Tb_cliente.ingresarTelefonosCliente(telefono.getText(), cedula.getText(), co.getC());
                    Tb_cliente.ingresarTelefonosCliente(tlf.getText(), cedula.getText(), co.getC());
                    co.cerrarConexion();
                    VentanaDialogo.dialogoAccion();
                    limpiarCampos();
                } else {
                    VentanaDialogo.VentanaRegistroDuplicado();
                }
            } else {
                VentanaDialogo.dialogoAdvertencia();
            }
        });
    }

    /**
     * Este metodo obliga al usuario a escribir en los campos obligatorios
     *
     * @return true si todos los campos estan llenos
     */
    private boolean validar() {
        return !cedula.getText().equals("") && !nombres.getText().equals("")
                && !apellidos.getText().equals("") && !dir.getText().equals("")
                && !telefono.getText().equals("");
    }

    /**
     * Carga los datos del cliente ingresado, se usa en PaneIngresarPedidos
     */
    public void cargarDatos() {
        cedula.setText(PaneIngresarPedidos.f.getCi_cliente());
        nombres.setText(PaneIngresarPedidos.f.getNombres());
        apellidos.setText(PaneIngresarPedidos.f.getApellidos());
        dir.setText(PaneIngresarPedidos.f.getDireccion());
        email.setText(PaneIngresarPedidos.f.getEmail());
        telefono.setText(PaneIngresarPedidos.f.getTelefono1());
        tl1 = PaneIngresarPedidos.f.getTelefono1();
        tlf.setText(PaneIngresarPedidos.f.getTelefono2());
        tl2 = PaneIngresarPedidos.f.getTelefono2();
    }

    /**
     *
     */
    private void limpiarCampos() {
        cedula.setText("");
        nombres.setText("");
        apellidos.setText("");
        dir.setText("");
        email.setText("");
        telefono.setText("");
        tlf.setText("");
    }

    /**
     * Permite actualizar la info del cliente Validaciones: ninguna
     */
    private void ModificarCliente() {
        modificar.setOnAction(e -> {
            co.connect();
            Tb_cliente.actualizarDatosCliente(cedula.getText(), nombres.getText(), apellidos.getText(), dir.getText(), email.getText(), co.getC());
            if (tl1 == null) {
                Tb_cliente.ingresarTelefonosCliente(telefono.getText(), tl1, co.getC());
            } else {
                Tb_cliente.actualizarTelefonosCliente(telefono.getText(), tl1, cedula.getText(), co.getC());
            }
            if (tl2 == null) {
                Tb_cliente.ingresarTelefonosCliente(tlf.getText(), tl2, co.getC());
            } else {
                Tb_cliente.actualizarTelefonosCliente(tlf.getText(), tl2, cedula.getText(), co.getC());
            }
            co.cerrarConexion();
            VentanaDialogo.dialogoAccion();
        });
    }

    public void showWindow() {
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 400, 500);
        stageForm.setTitle("Cliente");
        stageForm.setScene(scene);
        Image image = new Image("/recursos/logo.png");
        stageForm.getIcons().add(image);
        stageForm.resizableProperty().setValue(Boolean.FALSE);
        stageForm.show();

    }
}
