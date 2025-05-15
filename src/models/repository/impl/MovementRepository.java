package models.repository.impl;

import models.Movimiento;
import models.repository.CRUDRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovementRepository extends CRUDRepository<Movimiento> {

    @Override
    public void create(Movimiento movimiento) {
        String sql = "INSERT INTO movimiento (fecha, descripcion, monto, cuenta_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(movimiento.getFecha().getTime()));
            ps.setString(2, movimiento.getDescripcion());
            ps.setDouble(3, movimiento.getMonto());
            ps.setInt(4, movimiento.getCuentaId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar movimiento", e);
        }
    }

    @Override
    public void update(Movimiento movimiento) {
        throw new UnsupportedOperationException("Función no implementada.");
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM movimiento WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar movimiento", e);
        }
    }

    @Override
    public List<Movimiento> list() {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT fecha, descripcion, monto, cuenta_id FROM movimiento";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Movimiento movimiento = new Movimiento(
                        rs.getTimestamp("fecha"),
                        rs.getString("descripcion"),
                        rs.getDouble("monto"),
                        rs.getInt("cuenta_id")
                );
                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar movimientos", e);
        }

        return movimientos;
    }
}
