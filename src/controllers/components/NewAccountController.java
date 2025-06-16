package controllers.components;

import core.Controller;
import models.AccountModel;
import models.BancoModel;
import entities.Cuenta;
import views.components.NewAccountView;

import java.util.HashMap;
import java.util.Map;

public class NewAccountController extends Controller {
    private NewAccountView newAccountView;

    private final BancoModel bancoModel;
    private final AccountModel accountModel;
    private final Map<String, Cuenta.Builder> builders = new HashMap<>();

    public NewAccountController(BancoModel bancoModel, AccountModel accountModel) {
        this.bancoModel = bancoModel;
        this.accountModel = accountModel;
    }

    @Override
    public void run() {
        newAccountView = new NewAccountView(this);

        builders.put("Ahorro", Cuenta.Builder.cuentaAhorroBuilder());
        builders.put("Corriente", Cuenta.Builder.cuentaCorrienteBuilder());
        builders.put("Crédito", Cuenta.Builder.cuentaCreditoBuilder());
        setupViewComponents();
    }

    private void setupViewComponents() {
        newAccountView.getCb_type().addActionListener(e -> {
            String tipo = (String) newAccountView.getCb_type().getSelectedItem();
            boolean needsLimit = !(tipo != null && tipo.equals("Ahorro"));
            newAccountView.getLbl_limit().setVisible(needsLimit);
            newAccountView.getTf_limit().setVisible(needsLimit);
        });

        newAccountView.getBtn_create().addActionListener(e -> {
            try {
                String type = (String) newAccountView.getCb_type().getSelectedItem();
                double saldo = Double.parseDouble(newAccountView.getTf_saldo().getText());
                double limit = newAccountView.getTf_limit().getText().isEmpty() ? 0.0 : Double.parseDouble(newAccountView.getTf_limit().getText());

                Cuenta.Builder builder = builders.get(type);
                if (builder == null) {
                    bancoModel.log("Tipo de cuenta no válido: " + type);
                    return;
                }
                builder.saldo(saldo)
                        .limite(limit);
                Cuenta cuenta = builder.build();

                String notice = accountModel.saveAccount(cuenta);
                bancoModel.log(notice);
            } catch (NumberFormatException ex) {
                bancoModel.log("Error al crear la cuenta, ingrese valores numéricos válidos\n");
            } catch (Exception ex) {
               bancoModel.log("Error al guardar la cuenta: " + ex.getMessage() + "\n");
            }
        });
    }

    public NewAccountView getView() {
        return newAccountView;
    }

}
