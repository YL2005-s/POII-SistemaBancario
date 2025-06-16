package controllers.components;

import core.Controller;
import models.AccountModel;
import models.BancoModel;
import entities.Cuenta;
import utilities.CuentaUtils;
import views.components.AccountsView;

import javax.swing.*;
import java.awt.*;

public class AccountsController extends Controller {
    private AccountsView accountsView;

    private final BancoModel bancoModel;
    private final AccountModel accountModel;

    public AccountsController(BancoModel bancoModel, AccountModel accountModel) {
        this.bancoModel = bancoModel;
        this.accountModel = accountModel;
    }

    @Override
    public void run() {
        accountsView = new AccountsView(this);

        setupViewComponents();
    }

    private void setupViewComponents() {
        accountModel.getCuentas().forEach(accountsView.getCb_account()::addItem);
        accountsView.getCb_account().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cuenta c) {
                    setText(String.format("Cuenta %d - %s", c.getId(), CuentaUtils.getType(c)));
                }
                return this;
            }
        });

        accountsView.getCb_account().addActionListener(e -> {
            Cuenta cuenta = (Cuenta) accountsView.getCb_account().getSelectedItem();

            if (cuenta == null) {
                bancoModel.log("CLEAR:Error: Seleccione una cuenta válida");
                return;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("CLEAR:Detalles de la Cuenta\n\n");
            builder.append(String.format("ID: %d\n", cuenta.getId()));
            builder.append(String.format("Saldo actual: $%.2f\n", cuenta.getSaldo()));
            builder.append(String.format("Tipo de cuenta: %s\n", CuentaUtils.getType(cuenta)));
            builder.append(String.format("Genera Interés: %s\n", cuenta.isGeneraInteres() ? "Sí" : "No"));
            builder.append(String.format("Tasa de Interés: %.2f%%\n", cuenta.getTasaInteres() * 100));
            builder.append(String.format("Cuota de Mantenimiento: $%.2f\n", cuenta.getCuotaMantenimiento()));
            builder.append(String.format("Tiene Límite: %s\n", cuenta.isTieneLimite() ? "Sí" : "No"));
            builder.append(String.format("Límite: %s\n", cuenta.isTieneLimite() ? String.format("$%.2f", cuenta.getLimite()) : "N/A"));
            builder.append(String.format("Estado: %s\n", cuenta.isEstadoActiva() ? "Activa" : "Inactiva"));

            bancoModel.log(builder.toString());
        });
    }

    public void refreshComboBoxes() {
        accountsView.getCb_account().removeAllItems();
        accountModel.getCuentas().forEach(cuenta -> accountsView.getCb_account().addItem(cuenta));
        accountsView.getCb_account().repaint();
    }

    public AccountsView getView() {
        return accountsView;
    }
}
