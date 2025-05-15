package controllers.components;

import core.Controller;
import models.*;
import views.components.MovementsView;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class MovementsController extends Controller {
    private MovementsView movementsView;

    private final BancoModel bancoModel;
    private final AccountModel accountModel;
    private final MovementModel movementModel;

    public MovementsController(BancoModel bancoModel, AccountModel accountModel, MovementModel movementModel) {
        this.bancoModel = bancoModel;
        this.accountModel = accountModel;
        this.movementModel = movementModel;
    }

    @Override
    public void run() {
        movementsView = new MovementsView(this);

        setupViewComponents();
    }

    private void setupViewComponents() {
        accountModel.getCuentas().forEach(movementsView.getCb_account()::addItem);
        movementsView.getCb_account().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cuenta c) {
                    setText(String.format("Cuenta %d - %s", c.getId(), CuentaUtils.getType(c)));
                }
                return this;
            }
        });

        movementsView.getBtn_show().addActionListener(e -> {
            Cuenta cuenta = (Cuenta) movementsView.getCb_account().getSelectedItem();

            if (cuenta == null) {
                bancoModel.log("CLEAR:Error: Seleccione una cuenta válida");
                return;
            }

            List<Movimiento> movements = movementModel.getMovimientos().stream()
                    .filter(m -> m.getCuentaId() == cuenta.getId())
                    .toList();

            if (movements.isEmpty()) {
                bancoModel.log("CLEAR:Movimientos de Cuenta " + cuenta.getId() + "\n\n");
                bancoModel.log("No hay movimientos para esta cuenta\n");
                return;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("CLEAR:Movimientos de Cuenta ").append(cuenta.getId()).append(":\n\n");
            builder.append("Fecha\t\tDescripción\t\tMonto\n");
            builder.append("------------------------------------------------------------\n");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            double totalIngresos = movements.stream()
                    .filter(m -> m.getMonto() > 0)
                    .mapToDouble(Movimiento::getMonto)
                    .sum();

            double totalEgresos = movements.stream()
                    .filter(m -> m.getMonto() < 0)
                    .mapToDouble(Movimiento::getMonto)
                    .sum();

            movements.forEach(m -> {
                String fechaStr = sdf.format(m.getFecha());
                String signo = m.getMonto() < 0 ? "-$" : "+$";
                builder.append(String.format("%s\t%s\t\t%s%.2f\n\n",
                        fechaStr, m.getDescripcion(), signo, Math.abs(m.getMonto())));
            });

            builder.append(String.format("TOTAL INGRESOS: %.2f\n", totalIngresos));
            builder.append(String.format("TOTAL EGRESOS: %.2f\n", totalEgresos));
            builder.append(String.format("SALDO NETO: %.2f\n", (totalIngresos + totalEgresos)));

            bancoModel.log(builder.toString());
        });
    }

    public void refreshComboBoxes() {
        movementsView.getCb_account().removeAllItems();
        accountModel.getCuentas().forEach(cuenta -> movementsView.getCb_account().addItem(cuenta));
        movementsView.getCb_account().repaint();
    }

    public MovementsView getView() {
        return movementsView;
    }
}
