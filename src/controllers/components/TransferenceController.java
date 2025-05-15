package controllers.components;

import core.Controller;
import models.*;
import views.components.TransferenceView;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TransferenceController extends Controller {
    private TransferenceView transferenceView;

    private final BancoModel bancoModel;
    private final AccountModel accountModel;
    private final MovementModel movementModel;

    public TransferenceController(BancoModel bancoModel, AccountModel accountModel, MovementModel movementModel) {
        this.bancoModel = bancoModel;
        this.accountModel = accountModel;
        this.movementModel = movementModel;
    }

    @Override
    public void run() {
        transferenceView = new TransferenceView(this);

        setupViewComponents();
    }

    private void setupViewComponents() {
        setupCombo(transferenceView.getCb_origin());
        setupCombo(transferenceView.getCb_destination());

        transferenceView.getBtn_transfer().addActionListener(e -> {
            try {
                Cuenta origen = (Cuenta) transferenceView.getCb_origin().getSelectedItem();
                Cuenta destino = (Cuenta) transferenceView.getCb_destination().getSelectedItem();
                double monto = Double.parseDouble(transferenceView.getTf_monto().getText());

                if (origen == null || destino == null) {
                    bancoModel.log("Error: Seleccione ambas cuentas\n");
                    return;
                }

                if (origen.getId() == destino.getId()) {
                    bancoModel.log("Error: No puede transferir a la misma cuenta\n");
                    return;
                }

                if (origen.getSaldo() < monto) {
                    bancoModel.log("Error: Saldo insuficiente");
                    bancoModel.log("Saldo disponible: " + origen.getSaldo() + "\n");
                    return;
                }

                origen.setSaldo(origen.getSaldo() - monto);
                destino.setSaldo(destino.getSaldo() + monto);

                Movimiento mov1 = new Movimiento(new Date(), "Transferencia a " + destino.getId(), -monto, origen.getId());
                Movimiento mov2 = new Movimiento(new Date(), "Transferencia desde " + origen.getId(), +monto, destino.getId());

                bancoModel.log(movementModel.saveMovement(mov1));
                bancoModel.log(movementModel.saveMovement(mov2));

                bancoModel.log(accountModel.updateAccount(origen));
                bancoModel.log(accountModel.updateAccount(destino));

                bancoModel.log("Transferencia exitosa!");
                bancoModel.log(String.format("%d (%s) - Saldo: %.2f", origen.getId(), CuentaUtils.getType(origen), origen.getSaldo()));
                bancoModel.log(String.format("%d (%s) - Saldo: %.2f", destino.getId(), CuentaUtils.getType(destino), destino.getSaldo())  + "\n");

                transferenceView.getCb_origin().repaint();
                transferenceView.getCb_destination().repaint();
            } catch (NumberFormatException ex) {
                bancoModel.log("Error: Ingrese un monto válido\n");
            } catch (Exception ex) {
                bancoModel.log("Error al realizar la transferencia: " + ex.getMessage() + "\n");
            }
        });
    }

    private void setupCombo(JComboBox<Cuenta> combo) {
        accountModel.getCuentas().forEach(combo::addItem);
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cuenta c) {
                    setText(String.format("%d (%s) - Saldo: %.2f", c.getId(), CuentaUtils.getType(c), c.getSaldo()));
                }
                return this;
            }
        });
    }

    public void refreshComboBoxes() {
        transferenceView.getCb_origin().removeAllItems();
        transferenceView.getCb_destination().removeAllItems();

        accountModel.getCuentas().forEach(cuenta -> {
            transferenceView.getCb_origin().addItem(cuenta);
            transferenceView.getCb_destination().addItem(cuenta);
        });

        transferenceView.getCb_origin().repaint();
        transferenceView.getCb_destination().repaint();
    }

    public TransferenceView getView() {
        return transferenceView;
    }

}
