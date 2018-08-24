/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Principal
 */
public class MiniPaneReportes {
    private BorderPane root;
    private Button venta;
    private Button cliente;
    private Stage stage;
    private Conexion conn ;
    
    public MiniPaneReportes() {
        root= new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.SEASHELL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        conn= new Conexion();
        menuReporte();        
        generarReporteVenta();      
        generarMejorCliente();
    }

    public Pane getRoot() {
        return root;
    }
    
    private void menuReporte(){
        VBox vb= new VBox();
        venta= new Button("Reporte de Venta por Vendedor");
        cliente=new Button("Reporte Cliente Frecuentes");
        vb.getChildren().addAll(venta,cliente);
        vb.setSpacing(30);
        vb.setPadding(new Insets(30, 0, 50, 30));
        root.setCenter(vb);        
    }
    
    public void showWindow() {
    stage = new Stage();
    Scene scene = new Scene(getRoot(), 220, 180);
    stage.setTitle("Reportes");
    stage.setScene(scene);
    Image image = new Image("/recursos/reporte.png");
    stage.getIcons().add(image);
    stage.resizableProperty().setValue(Boolean.FALSE);
    stage.show();

    }
    /*
    Con este metodo "se genera el archivo" *sin graficos* de las ventas por vendedor
    */
    private void generarReporteVenta(){
        
        venta.setOnAction(e->{
            try {
                conn.connect();
                
                JasperReport reporte = null;
                String path= "src/Reportes/VentaVendedores.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jprint= JasperFillManager.fillReport(reporte,null,conn.getC()); 
                JasperViewer view = new JasperViewer(jprint,false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
                conn.cerrarConexion();   //Cierra la conexion
            } catch (JRException ex) {
                Logger.getLogger(MiniPaneReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void generarMejorCliente(){
        
        cliente.setOnAction(e->{
            try {
                conn.connect();
                
                JasperReport reporte = null;
                String path= "src/Reportes/MejorCliente.jasper";
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jprint= JasperFillManager.fillReport(reporte,null,conn.getC()); 
                JasperViewer view = new JasperViewer(jprint,false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
                conn.cerrarConexion();   //Cierra la conexion
            } catch (JRException ex) {
                Logger.getLogger(MiniPaneReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    
}

