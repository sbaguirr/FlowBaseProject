/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Main.Proyecto;
import controlador.CONSTANTES;
import controlador.VentanaDialogo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import modelo.Tb_cliente;
import modelo.Tb_pedido;

/**
 *
 * @author Tiffy
 */
public class PaneVerClientes {

    private BorderPane root;
    private TableView cliente, pedidos;
    private Button buscar;
    private TextField ci, nom, ape;
    private RadioButton  c, n;
    private ObservableList<Tb_cliente> listaClientes;
    private ObservableList<Tb_pedido> listaPedidos;
    private Conexion co;

    public PaneVerClientes() {
        root = new BorderPane();
        co = new Conexion();
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        llamarMetodos();
    }

    public Pane getRoot() {
        return root;
    }

    private void llamarMetodos() {
        inicializarObjetos();
        seccionIzquierda();
        seccionCentro();
        habilitarCedula();
        habilitarNombre();
        buscar();
        back();
    }

    private void inicializarObjetos() {
        cliente = new TableView();
        pedidos = new TableView();
        buscar = new Button("Buscar");
        ci = new TextField();
        ci.setDisable(true);
        nom = new TextField();
        nom.setPromptText("Nombres");
        nom.setDisable(true);
        ape = new TextField();
        ape.setPromptText("Apellidos");
        ape.setDisable(true);
        ToggleGroup grupo = new ToggleGroup();
        c = new RadioButton("Cédula");
        c.setToggleGroup(grupo);
        n = new RadioButton("Nombres");
        n.setToggleGroup(grupo);
    }

    private HBox encabezado() {
        HBox h = new HBox();
        Label titulo = new Label("Ver Clientes");
        titulo.setFont(new Font("Verdana", 30));
        titulo.setStyle("-fx-text-fill: #E4C953;");
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10, 10, 25, 10));
        h.getChildren().add(titulo);
        return h;
    }

    private void seccionIzquierda() {
        GridPane gp = new GridPane();
        VBox vb = new VBox();
        VBox x = new VBox();
        x.getChildren().addAll(nom, ape);
        gp.addRow(0, c, ci);
        gp.addRow(1, n, x);
        gp.add(buscar, 4, 0);
        gp.setHgap(10);
        gp.setVgap(5);
        vb.setPadding(new Insets(0, 0, 10, 50)); //top,derecha,abajo,izquierda
        vb.setSpacing(5);
        vb.getChildren().addAll(encabezado(), gp);
        root.setTop(vb);
    }

    private void seccionCentro() {
        VBox vb = new VBox();
        TableColumn c = new TableColumn<>("Ci");
        TableColumn nom = new TableColumn<>("Nombres");
        TableColumn ape = new TableColumn<>("Apellidos");
        TableColumn mail = new TableColumn<>("E-mail");
        TableColumn tl = new TableColumn<>("Teléfono 1"); 
        TableColumn tl2 = new TableColumn<>("Teléfono 2");
        TableColumn dir = new TableColumn<>("Dirección");
        c.setPrefWidth(100);
        c.setCellValueFactory(new PropertyValueFactory<>("ci_cliente"));
        propertiesTableView(c);
        nom.setPrefWidth(160);
        nom.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        propertiesTableView(nom);
        ape.setPrefWidth(160);
        ape.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        propertiesTableView(ape);
        tl.setPrefWidth(100);
        tl.setCellValueFactory(new PropertyValueFactory<>("telefono1"));
        propertiesTableView(tl);
        tl2.setPrefWidth(100);
        tl2.setCellValueFactory(new PropertyValueFactory<>("telefono2"));
        propertiesTableView(tl2);
        mail.setPrefWidth(110);
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        propertiesTableView(mail);
        dir.setPrefWidth(358);
        dir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        propertiesTableView(dir);
        cliente.getColumns().addAll(c, nom, ape, tl, tl2, mail, dir); //chanfe
        cliente.setPrefSize(1000, 100);
        vb.setPadding(new Insets(0, 10, 15, 10)); //top, derecha,abajo,izquierda+
        vb.getChildren().addAll(cliente, seccionCentro2());
        root.setCenter(vb);
    }

    /**
     * Método que cargará la información de los clientes a partir de su ci en el
     * TableView
     */
    private void cargarContenidoPorCi() {
        List<Tb_cliente> q = new ArrayList<>();
        co.connect();
        Tb_cliente p = Tb_cliente.buscarCliente(ci.getText(), co.getC());
        List<Tb_pedido> pd= Tb_pedido.pedidosXClienteCi(ci.getText(), co.getC());
        co.cerrarConexion();
        if (p.getCi_cliente()== null) {
            VentanaDialogo.VentanaRegistroNoEncontrado();
        } else {
            q.add(p);
            listaClientes = FXCollections.observableList(q);
            listaPedidos= FXCollections.observableList(pd);
            cliente.setItems(listaClientes);
            pedidos.setItems(listaPedidos);
        }
    }
    
    /**
     * Método que cargará la información de los clientes a partir del nombre en el
     * TableView
     */
    private void cargarContenidoPorNombres() {
        List<Tb_cliente> q = new ArrayList<>();
        co.connect();
        Tb_cliente p = Tb_cliente.buscarClientePorNombres(nom.getText(), ape.getText(), co.getC());
        List<Tb_pedido> pd= Tb_pedido.pedidosXClienteNom(nom.getText(),ape.getText() , co.getC());
        co.cerrarConexion();
        if (p.getCi_cliente() != null) {
            q.add(p);
            listaClientes = FXCollections.observableList(q);
            listaPedidos= FXCollections.observableList(pd);
            cliente.setItems(listaClientes);
            pedidos.setItems(listaPedidos);
        } else {
            VentanaDialogo.VentanaRegistroNoEncontrado();
        }

    }
    
    
    /**
     * 
     * @return 
     */
    private VBox seccionCentro2() {
        VBox vb = new VBox();
        TableColumn c1 = new TableColumn<>("C");
        TableColumn fe = new TableColumn<>("Fecha Pedido");
        TableColumn obs = new TableColumn<>("Observaciones");
        TableColumn fee = new TableColumn<>("Fecha entrega");
        TableColumn hen = new TableColumn<>("Hora entrega");
        TableColumn cli = new TableColumn<>("Nombres Cliente");
        TableColumn cli2 = new TableColumn<>("Apellidos Cliente");
        TableColumn rem = new TableColumn<>("Vendedor");
        TableColumn tra = new TableColumn<>("Nombres Destinatario");
        TableColumn tra2 = new TableColumn<>("Apellidos Destinatario");
        TableColumn cant = new TableColumn<>("Estado");
        fe.setPrefWidth(80);
        fe.setCellValueFactory(new PropertyValueFactory<>("fecha_pedido"));
        propertiesTableView(fe);
        obs.setPrefWidth(200);
        obs.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        propertiesTableView(obs);
        fee.setPrefWidth(80);
        fee.setCellValueFactory(new PropertyValueFactory<>("fecha_entrega"));
        propertiesTableView(fee);
        hen.setPrefWidth(80);
        hen.setCellValueFactory(new PropertyValueFactory<>("hora_entrega"));
        propertiesTableView(hen);
        cli.setPrefWidth(100);
        cli.setCellValueFactory(new PropertyValueFactory<>("clienteNom"));
        propertiesTableView(cli);
        cli2.setPrefWidth(100);
        cli2.setCellValueFactory(new PropertyValueFactory<>("clienteApe"));
        propertiesTableView(cli2);
        rem.setPrefWidth(80);
        rem.setCellValueFactory(new PropertyValueFactory<>("ci_trabajador"));
        propertiesTableView(rem);

        tra.setPrefWidth(130);
        tra.setCellValueFactory(new PropertyValueFactory<>("destinatarioNom"));
        propertiesTableView(tra);
        tra2.setPrefWidth(130);
        tra2.setCellValueFactory(new PropertyValueFactory<>("destinatarioApe"));
        propertiesTableView(tra2);

        cant.setPrefWidth(100);
        cant.setCellValueFactory(new PropertyValueFactory<>("estado"));
        propertiesTableView(cant);

        c1.setPrefWidth(25);
        c1.setCellValueFactory(new PropertyValueFactory<>("cod_pedido"));
        propertiesTableView(c1);
        pedidos.getColumns().addAll(c1, fe, fee, hen, cli, cli2, rem, tra, tra2, cant, obs);
        pedidos.setPrefSize(1000, 250);
        vb.setPadding(new Insets(10, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().add(pedidos);
        return vb;

    }


    private void habilitarCedula() {
        c.setOnAction(e -> {
            ci.setDisable(false);
            nom.setText("");
            nom.setDisable(true);
            ape.setText("");
            ape.setDisable(true);
        });

    }
 
    private void habilitarNombre() {
        n.setOnAction(e -> {
            nom.setDisable(false);
            ape.setDisable(false);
            ci.setDisable(true);
            ci.setText("");
        });
    }
    
     private void buscar() {
        buscar.setOnAction(e -> {
            if (c.isSelected()) {
                if (!ci.getText().equals("")) {
                    cargarContenidoPorCi();
                } else {
                    VentanaDialogo.dialogoAdvertencia();
                }
            } else if (n.isSelected()) {
                if (!nom.getText().equals("") && !ape.getText().equals("")) {
                    cargarContenidoPorNombres();
                } else {
                    VentanaDialogo.dialogoAdvertencia();
                }
            } else {
                VentanaDialogo.dialogoAdvertencia();
            }

        });
    }


    /**
     * Método que permite modificar una columna de un TableView
     *
     * @param c
     */
    private void propertiesTableView(TableColumn c) {
        c.setSortable(false);
        c.setResizable(true);
        c.setEditable(false);
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
