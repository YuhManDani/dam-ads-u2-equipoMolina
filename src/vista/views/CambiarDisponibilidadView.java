package vista.views;

import servicio.ClubDeportivo;
import modelo.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CambiarDisponibilidadView extends GridPane {

    Connection con;
    {
        try {
            con = data.dbutils.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public CambiarDisponibilidadView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);



        ComboBox<Pista> id = new ComboBox();
        CheckBox disponible = new CheckBox("Disponible");
        Button cambiar = new Button("Aplicar");


        ArrayList<Pista> pistas = club.getPistas();
        for (Pista pista : pistas) {
            id.getItems().add(pista);
        }


        addRow(0, new Label("idPista"), id);
        addRow(1, new Label("Estado"), disponible);
        add(cambiar, 1, 2);

        cambiar.setOnAction(e -> {

            Pista pistaSeleccionada = id.getValue();
            if (pistaSeleccionada == null) {
                showError("No has seleccionado una pista");
            }
            String idPista = pistaSeleccionada.getIdPista();
            try {
                club.CambiarDisponibilidad(idPista, disponible.isSelected());
                showInfo("Pista modificada correctamente");
            } catch (SQLException ex) {
              showError("Error en la modificacion: " + ex.getMessage());
            }
        });
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }
    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
