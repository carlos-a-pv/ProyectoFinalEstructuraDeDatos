package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Rol;
import model.Usuario;

import java.io.IOException;
import java.util.Collection;

import static controller.AppController.INSTANCE;

public class CrearUsuarioController {

    @FXML
    private TextField tfUserId;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnCrearUsuario;
    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private Button btnCancelar;

    @FXML
    void initialize(){
        cbRol.setItems(FXCollections.observableArrayList(Rol.values()));
    }
    public void onClickCrearUsuario( ) {

        String userId = tfUserId.getText();
        String password = tfPassword.getText();
        Rol rol = cbRol.getValue();
        if(userId.equals("") || password.equals("") || rol == null){
            mostrarMensaje("Error en la creación del usuario", "Datos invalidos", "Llene los campos correctamente", Alert.AlertType.WARNING);
        }else{
            Usuario usuarioCreado = INSTANCE.getModel().crearCuentasDeUsuario(userId, password, rol);
            if(usuarioCreado != null){
                Stage stage = new Stage();
                stage.initOwner(btnCrearUsuario.getScene().getWindow());
                btnCrearUsuario.getScene().getWindow().hide();
                mostrarMensaje("Creación usuario", "EXITO","Se ha creado el usuario correctamente", Alert.AlertType.INFORMATION);
            }else{
                tfUserId.setText("");
                tfPassword.setText("");
                cbRol.setValue(null);
                mostrarMensaje("Creación usuario", "ERROR", "ha ocurrido un error creando el usuario", Alert.AlertType.ERROR);
            }
        }
    }

    public void onClickCancelar(){
        Stage stage = new Stage();
        stage.initOwner(btnCancelar.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
