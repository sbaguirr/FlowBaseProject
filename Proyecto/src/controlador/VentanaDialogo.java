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
     * Método que permitirá mostrar una ventana cuando el usuario deje algún
     * campo vacío
     */
    public static void dialogoAdvertenciaPedido() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Debe asegurarse de seleccionar el método de búsqueda y el estado del pedido");
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
     * Método que permitirá mostrar una ventana cuando ya exista un registro buscado
     */
    public static void VentanaRegistroDuplicado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("El registro que está ingresando ya existe");
        alert.showAndWait();
    }
    
    /** 
     * Método que permitirá mostrar una ventana cuando se haya agregado un articulo correctamente
     */
    public static void ProductoGuardadoExitosamente() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Agregar nuevo producto");
        alert.setHeaderText(null);
        alert.setContentText("El producto ha sido guardado exitosamente");
        alert.showAndWait();
    }
    
    /** 
     * Método que permitirá mostrar una ventana cuando se no se haya agregado un articulo 
     */
    public static void ProductoGuardadoFallido() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Agregar nuevo artículo");
        alert.setHeaderText("El producto no ha sido guardado exitosamente");
        alert.setContentText("Por favor, asegúrese de llenar todos los campos");
        alert.showAndWait();
    }
    
    public static void ProductoEliminadoExitosamente() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminar artículo");
        alert.setHeaderText(null);
        alert.setContentText("El producto ha sido eliminado exitosamente");
        alert.showAndWait();
    }
    
    public static void ProductoModificadoExitosamente() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modificar artículo");
        alert.setHeaderText(null);
        alert.setContentText("El producto ha sido modificado exitosamente");
        alert.showAndWait();
    }
    
    public static void ProductoModificadoFallido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modificar artículo");
        alert.setHeaderText(null);
        alert.setContentText("El producto  no se ha modificado correctamente");
        alert.showAndWait();
    }
    
    public static void ProductoEliminadoFallido() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Eliminar artículo");
        alert.setHeaderText("El producto no ha sido borrado");
        alert.setContentText("El producto no se encuentra registrado");
        alert.showAndWait();
    }
    
    public static void CampoVacio() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Artículo");
        alert.setHeaderText("No se puede realizar la acción solicitada");
        alert.setContentText("Por favor, asegúrese de llenar todos los campos");
        alert.showAndWait();
    }

    public static void Numerico(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Artículo");
        alert.setHeaderText("No se puede ingresar solo números como código");
        alert.setContentText("Por favor, asegúrese de ingresar un valor no numérico únicamente");
        alert.showAndWait();
    }
    
    public static void noNumerico() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Artículo");
        alert.setHeaderText("No se puede realizar la acción solicitada");
        alert.setContentText("Por favor, asegúrese de ingresar un número");
        alert.showAndWait();
    }
    
      /**
     * Método que permitirá mostrar una ventana el usuario cuando no haya stock suficiente
     */
    public static void dialogoAdvertencia3() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("No se pudo ingresar el producto, verifique el stock");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }
    
     public static void dialogoAdvertencia4() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Se produjo un error, intente nuevamente");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }
     
    public static void PedidoGuardadoFallido() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Agregar nuevo pedido");
        alert.setHeaderText("El pedido no ha sido guardado exitosamente");
        alert.setContentText("Por favor, asegúrese de llenar todos los campos");
        alert.showAndWait();
    }
}
