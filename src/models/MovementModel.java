package models;

import entities.Movimiento;
import models.repository.impl.MovementRepository;

import java.util.ArrayList;
import java.util.List;

public class MovementModel {
    private final MovementRepository repository;
    private final List<Movimiento> movimientos = new ArrayList<>();
    private String notice;

    public MovementModel() {
        this.repository = new MovementRepository();
    }

    public String saveMovement(Movimiento mov) {
        try {
            repository.create(mov);
            movimientos.add(mov);
        } catch (Exception ex) {
            notice = "Error al guardar movimiento";
        }

        return notice;
    }

    public String loadMovements() {
        try {
            movimientos.addAll(repository.list());
        } catch (Exception e) {
            notice = "Error general al cargar movimientos: " + e.getMessage();
        }

        return notice;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}
