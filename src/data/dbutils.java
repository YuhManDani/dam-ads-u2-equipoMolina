package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbutils {

    // 1. Variable estática para la única instancia de la clase
    private static dbutils instance;

    // 2. Variable para la conexión
    private Connection con;

    private final String url = "jdbc:mysql://localhost:3306/club_dama";
    private final String user = "root";
    private final String password = "1234";

    /**
     * 3. Constructor PRIVADO:
     * Evita que se pueda crear una instancia con 'new dbutils()'.
     */
    private dbutils() throws SQLException {
        // La lógica de conexión va aquí
        this.con = DriverManager.getConnection(url, user, password);
        System.out.println("Conexión inicializada.");
    }

    /**
     * 4. Método ESTÁTICO y PÚBLICO para obtener la instancia única.
     * Si no existe, la crea; si ya existe, devuelve la existente.
     */
    public static dbutils getInstance() throws SQLException {
        if (instance == null) {
            // Asegura que sea seguro en entornos multihilo (aunque hay mejores formas)
            synchronized (dbutils.class) {
                if (instance == null) {
                    instance = new dbutils();
                }
            }
        }
        return instance;
    }

    /**
     * 5. Método público para obtener la conexión (usado por los servicios)
     */
    public Connection getConnection() {
        return this.con;
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                // Manejo de errores
            }
        }
    }
}