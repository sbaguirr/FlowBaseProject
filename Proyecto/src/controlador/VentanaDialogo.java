/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Tiffy
 */
public class VentanaDialogo {
    private VentanaDialogo(){}
    
    
    /**
     * Método que permitirá mostrar una ventana cuando se produzca una excepcion
     */
    public static void VentanaProblemasTecnicos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Lo sentimos, estamos teniendo inconvenientes técnicos");
        alert.showAndWait();
    }

    /**
     * Método que permitirá mostrar una ventana cuando el usuario deje algún
     * campo vacío
     */
    public static void dialogoAdvertencia() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Debe asegurarse de llenar todos los campos");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    /**
     * Método que permitirá mostrar una ventana el usuario no seleccione algún registro
     */
    public static void dialogoAdvertencia2() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Debe asegurarse de seleccionar un registro de la tabla");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    /**
     * Método que permitirá saber que un registro se almacenó correctamente
     */
    public static void dialogoAccion() {
        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle("Registro Migratorio");
        advertencia.setContentText("Registro almacenado correctamente");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    /**
     * Método que permitirá mostrar una ventana cuando no exista un registro buscado
     */
    public static void VentanaRegistroNoEncontrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Lo sentimos,el registro que está buscando no existe");
        alert.showAndWait();
    }
    
    /** 
     * Método que permitirá mostrar una ventana cuando no exista un registro buscado
     */
    public static void VentanaRegistroDuplicado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("El registro que está ingresando ya existe");
        alert.showAndWait();
    }

}
