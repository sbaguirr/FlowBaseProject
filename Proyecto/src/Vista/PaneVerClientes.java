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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

/**
 *
 * @author Tiffy
 */
public class PaneVerClientes {

    private BorderPane root;
    private TableView cliente, pedidos;
    private Button buscar, modificar;
    private TextField ci, rc;
    private RadioButton ruc, c;

    public PaneVerClientes() {
        root = new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        inicializarObjetos();
        seccionIzquierda();
        seccionCentro() ;
        back();
    }

    public Pane getRoot() {
        return root;
    }

    private void inicializarObjetos() {
        cliente = new TableView();
        pedidos = new TableView();
        buscar = new Button("Buscar");
        modificar = new Button("Modificar");
        ci = new TextField();
        rc = new TextField();
        ToggleGroup grupo= new ToggleGroup();
        ruc = new RadioButton("Ruc");
        ruc.setToggleGroup(grupo);
        c = new RadioButton("Cédula");
        c.setToggleGroup(grupo);
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
        Label l = new Label("Cédula de cliente");
        Label r = new Label("Ruc empresa");
        gp.addRow(0, c, l, ci);
        gp.addRow(1, ruc, r, rc);
        gp.add(buscar,2,2);
        gp.setHgap(10);
        vb.setPadding(new Insets(0, 0, 10, 50)); //top,derecha,abajo,izquierda
        vb.setSpacing(5);
        vb.getChildren().addAll(encabezado(), gp);
        root.setTop(vb);
    }

    private void seccionCentro() {
        VBox vb = new VBox();
         TableColumn c = new TableColumn<>("CI");
        TableColumn nom = new TableColumn<>("Nombres");
        TableColumn ape = new TableColumn<>("Apellidos");
        TableColumn tld = new TableColumn<>("Teléfono");
        TableColumn mail = new TableColumn<>("E-mail");
        TableColumn tl2= new TableColumn<>("Teléfono 2"); 
        TableColumn dir = new TableColumn<>("Dirección");
        TableColumn r = new TableColumn<>("Ruc");
        TableColumn rnom = new TableColumn<>("Empresa");
        TableColumn rdir = new TableColumn<>("Dirección");
        TableColumn rtlf = new TableColumn<>("Teléfono");
        nom.setPrefWidth(110);
        //nom.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(nom);
        ape.setPrefWidth(100);
        //ape.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(ape);
        tld.setPrefWidth(80);
        //tld.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(tld);
        mail.setPrefWidth(100);
        //mail.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(mail);
        tl2.setPrefWidth(100);
        //tl2.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(tl2);
        
        dir.setPrefWidth(150);
        //dir.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(dir);
        r.setPrefWidth(100);
        //r.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(r);
        rnom.setPrefWidth(100);
        //rnom.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(rnom);
        
        rdir.setPrefWidth(150);
        //rdir.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(rdir);
        
        rtlf.setPrefWidth(100);
        //rtlf.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(rtlf);
        
        c.setPrefWidth(100);
        //c.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(c);
        cliente.getColumns().addAll(c,nom, ape, tld, tl2,mail,dir,r,rnom,rdir,rtlf);
        cliente.setPrefSize(1000, 250);
        vb.setPadding(new Insets(0, 10, 15, 10)); //top, derecha,abajo,izquierda+
        vb.getChildren().addAll(cliente,seccionCentro2());
        root.setCenter(vb);

    }
    
     private VBox seccionCentro2() {
        VBox vb = new VBox();
         TableColumn c = new TableColumn<>("Código");
        TableColumn fe = new TableColumn<>("Fecha");
        TableColumn obs = new TableColumn<>("Observaciones");
        TableColumn fee = new TableColumn<>("Fecha entrega");
        TableColumn hen = new TableColumn<>("Hora entrega");
        TableColumn fp= new TableColumn<>("Forma de pago"); 
        TableColumn ra = new TableColumn<>("Articulo");
        TableColumn r = new TableColumn<>("Mensaje");
        TableColumn rem = new TableColumn<>("Empleado");
        TableColumn tra = new TableColumn<>("Destinatario");
        TableColumn cant = new TableColumn<>("Estado");
        fe.setPrefWidth(100);
        //fe.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(fe);
        obs.setPrefWidth(100);
        //obs.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(obs);
        fee.setPrefWidth(100);
        //fee.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(fee);
        hen.setPrefWidth(100);
        //hen.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(hen);
        fp.setPrefWidth(100);
        //fp.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(fp);
        
        ra.setPrefWidth(100);
        //ra.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(ra);
        r.setPrefWidth(100);
        //r.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(r);
        rem.setPrefWidth(100);
        //rem.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(rem);
        
        tra.setPrefWidth(100);
        //tra.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(tra);
        
        cant.setPrefWidth(100);
        //cant.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(cant);
        
        c.setPrefWidth(100);
        //c.setCellValueFactory(new PropertyValueFactory<>("ci_pasaporte"));
        propertiesTableView(c);
        pedidos.getColumns().addAll(c,fe, obs, fee, fp,hen,ra,r,rem,tra,cant);
        pedidos.setPrefSize(1000, 250);
        vb.setPadding(new Insets(10, 0, 15, 0)); //top, derecha,abajo,izquierda+
        vb.getChildren().add(pedidos);
       return vb;

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
            if (PaneMenuPrincipal.nombreUsuario.getText().equals("") && !PaneMenuPrincipalSucursal.nombreUsuario.getText().equals("")) {
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
