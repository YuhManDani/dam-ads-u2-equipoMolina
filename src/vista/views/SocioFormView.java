package vista.views;

import modelo.*;
import servicio.ClubDeportivo;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

public class SocioFormView extends GridPane {
    Connection con;
    {
        try {
            con = data.dbutils.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SocioFormView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        TextField id = new TextField();
        TextField dni = new TextField();
        TextField nombre = new TextField();
        TextField apellidos = new TextField();
        TextField tel = new TextField();
        TextField email = new TextField();
        Button crear = new Button("Crear");

        addRow(0, new Label("idSocio*"), id);
        addRow(1, new Label("DNI"), dni);
        addRow(2, new Label("Nombre"), nombre);
        addRow(3, new Label("Apellidos"), apellidos);
        addRow(4, new Label("Teléfono"), tel);
        addRow(5, new Label("Email"), email);
        add(crear, 1, 6);

        crear.setOnAction(e -> {
            try{
                String idSocio = id.getText();
                String dniSocio = dni.getText();
                String nombreSocio = nombre.getText();
                String apellidosSocio = apellidos.getText();
                String tlfno = tel.getText();
                String correo = email.getText();
                Socio socio = new Socio(idSocio,dniSocio,nombreSocio,apellidosSocio,tlfno,correo);
                club.darDeAltaSocio(socio);
            } catch (SQLException sqlException) {
                showError("Error en la inserción de socio" + sqlException.getMessage());
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
