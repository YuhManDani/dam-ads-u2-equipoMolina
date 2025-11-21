package servicio;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import modelo.*;

public class ClubDeportivo {
    Connection con;
    {
        try {
            con = data.dbutils.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Socio> getSocios() {
        ArrayList<Socio> socios = new ArrayList<>();
        String stmt = "SELECT * FROM socios";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String dni;
        String idSocio;
        String nombre;
        String apellidos;
        String telefono;
        String email;

        try {
            pstmt = con.prepareStatement(stmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dni = (rs.getString("dni"));
                idSocio = (rs.getString("id_socio"));
                nombre = (rs.getString("nombre"));
                apellidos = (rs.getString("apellidos"));
                telefono = (rs.getString("telefono"));
                email = (rs.getString("email"));
                Socio socio = new Socio(idSocio,dni,nombre,apellidos,telefono,email);
                socios.add(socio);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return socios;
    }

    public ArrayList<Pista> getPistas() {
        ArrayList<Pista> pistas = new ArrayList<>();
        String stmt = "SELECT * FROM pistas";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String idPista;
        String deporte;
        String descripcion;
        Boolean disponible;


        try {
            pstmt = con.prepareStatement(stmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                idPista = (rs.getString("id_pista"));
                deporte = (rs.getString("deporte"));
                descripcion = (rs.getString("descripcion"));
                disponible = (rs.getInt("disponible") == 1);
                Pista pista = new Pista(idPista,deporte,descripcion,disponible);
                pistas.add(pista);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pistas;
    }

    public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String stmt = "SELECT * FROM reservas";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String idReserva;
        String idSocio;
        String idPista;
        LocalDate fecha;
        LocalTime hora_inicio;
        Integer duracion_min;
        Double precio;

        try {
            pstmt = con.prepareStatement(stmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                idReserva = (rs.getString("id_reserva"));
                idSocio = (rs.getString("id_socio"));
                idPista = (rs.getString("id_pista"));
                fecha = rs.getDate("fecha").toLocalDate();
                hora_inicio = rs.getTime("hora_inicio").toLocalTime();
                duracion_min = rs.getInt("duracion_min");
                precio = rs.getDouble("precio");
                Reserva reserva = new Reserva(idReserva,idSocio,idPista,fecha,hora_inicio,duracion_min,precio);
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reservas;
    }

    public void darDeBajaSocio(Socio socioSeleccionado) throws SQLException {

        String socioId = socioSeleccionado.getIdSocio();

        String stmt = "DELETE FROM socios WHERE id_socio = ?";
        PreparedStatement ps = null;
        ps = con.prepareStatement(stmt);
        ps.setString(1, socioId);
        ps.executeUpdate();
    }

    public void darDeAltaSocio(Socio s) throws SQLException{
        String stmt = "INSERT INTO socios (id_socio, dni, nombre, apellidos, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        ps = con.prepareStatement(stmt);
        ps.setString(1, s.getIdSocio());
        ps.setString(2, s.getDni());
        ps.setString(3, s.getNombre());
        ps.setString(4, s.getApellidos());
        ps.setString(5, s.getTelefono());
        ps.setString(6, s.getEmail());
        ps.executeUpdate();
    }

    public void crearReserva(Reserva r) throws SQLException {
        String stmt = "call sp_crear_reserva(?, ?, ?, ?, ?, ?)";
        CallableStatement procedure = null;

        procedure = con.prepareCall(stmt);
        procedure.setString(1, r.getIdReserva());
        procedure.setString(2, r.getIdSocio());
        procedure.setString(3, r.getIdPista());
        procedure.setDate(4, java.sql.Date.valueOf(r.getFecha()));
        procedure.setTime(5, java.sql.Time.valueOf(r.getHoraInicio()));
        procedure.setInt(6, r.getDuracionMin());
        procedure.execute();
    }

    public void CambiarDisponibilidad(String idPista, Boolean disponible) throws SQLException {
        String stmt = "UPDATE pistas SET disponible = ? WHERE id_pista = ?";
        PreparedStatement pstmt = null;
            pstmt = con.prepareStatement(stmt);
            pstmt.setInt(1, disponible ? 1 : 0);
            pstmt.setString(2, idPista);
            pstmt.executeUpdate();
    }
  
    public void cancelarReserva(Reserva r) throws SQLException {
        String stmt = "Delete FROM reservas where id_reserva = ?";
        PreparedStatement ps = null;
        ps = con.prepareStatement(stmt);
        ps.setString(1, r.getIdReserva());
        ps.executeUpdate();
    }
}
