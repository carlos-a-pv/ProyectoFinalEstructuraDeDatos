package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Obligatoriedad;
import model.Tarea;

import static controller.AppController.INSTANCE;
public class CrearTareaController {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private ComboBox<Obligatoriedad> cbObligatoriedad;
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCrearTarea;

    @FXML
    void initialize(){
        cbObligatoriedad.setItems(FXCollections.observableArrayList(Obligatoriedad.values()));
    }

    public void onClickCancelar( ) {
        Stage stage = new Stage();
        stage.initOwner(btnCancelar.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
    }

    public void onClickCrearTarea( ) {

        String nombreTarea = tfNombre.getText();
        String descripcion = tfDescripcion.getText();
        Obligatoriedad obligatoriedad = cbObligatoriedad.getValue();

        if(nombreTarea.equals("") || descripcion.equals("") || obligatoriedad == null){
            mostrarMensaje("Advertencia", "Campos invalidos", "Complete los campos!", Alert.AlertType.WARNING);
        }else{
            Tarea tareaNueva = new Tarea(nombreTarea, descripcion, obligatoriedad);
            INSTANCE.getModel().insertarTareaAlFinal(INSTANCE.getModel().getProcesoSeleccionado(), INSTANCE.getModel().getActividadSeleccionada(), tareaNueva);

            Stage stage = new Stage();
            stage.initOwner(btnCrearTarea.getScene().getWindow());
            btnCrearTarea.getScene().getWindow().hide();
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
