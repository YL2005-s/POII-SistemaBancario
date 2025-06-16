package models.repository.impl;

import entities.Cuenta;
import models.repository.CRUDRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends CRUDRepository<Cuenta> {

    @Override
    public void create(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta (id, saldo, genera_interes, tasa_interes, cuota_mantenimiento, tiene_limite, limite, estado_activa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cuenta.getId());
            ps.setDouble(2, cuenta.getSaldo());
            ps.setBoolean(3, cuenta.isGeneraInteres());
            ps.setDouble(4, cuenta.getTasaInteres());
            ps.setDouble(5, cuenta.getCuotaMantenimiento());
            ps.setBoolean(6, cuenta.isTieneLimite());
            ps.setDouble(7, cuenta.getLimite());
            ps.setBoolean(8, cuenta.isEstadoActiva());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la cuenta", e);
        }
    }

    @Override
    public List<Cuenta> list() {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT id, saldo, genera_interes, tasa_interes, cuota_mantenimiento, tiene_limite, limite, estado_activa FROM cuenta";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cuenta cuenta = new Cuenta.Builder()
                        .id(rs.getInt("id"))
                        .saldo(rs.getDouble("saldo"))
                        .generaInteres(rs.getBoolean("genera_interes"))
                        .tasaInteres(rs.getDouble("tasa_interes"))
                        .cuotaMantenimiento(rs.getDouble("cuota_mantenimiento"))
                        .tieneLimite(rs.getBoolean("tiene_limite"))
                        .limite(rs.getDouble("limite"))
                        .estadoActiva(rs.getBoolean("estado_activa"))
                        .build();
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar cuentas", e);
        }

        return cuentas;
    }

    @Override
    public void update(Cuenta cuenta) {
        String sql = "UPDATE cuenta SET saldo = ?, genera_interes = ?, tasa_interes = ?, cuota_mantenimiento = ?, "
                + "tiene_limite = ?, limite = ?, estado_activa = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, cuenta.getSaldo());
            ps.setBoolean(2, cuenta.isGeneraInteres());
            ps.setDouble(3, cuenta.getTasaInteres());
            ps.setDouble(4, cuenta.getCuotaMantenimiento());
            ps.setBoolean(5, cuenta.isTieneLimite());
            ps.setDouble(6, cuenta.getLimite());
            ps.setBoolean(7, cuenta.isEstadoActiva());
            ps.setInt(8, cuenta.getId());

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas == 0) {
                throw new RuntimeException("No se encontró ninguna cuenta con id: " + cuenta.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cuenta", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM cuenta WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas == 0) {
                throw new RuntimeException("No se encontró ninguna cuenta con id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la cuenta", e);
        }
    }
}
