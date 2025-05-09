package controllers.components;

import core.Controller;
import models.AccountModel;
import models.BancoModel;
import models.Cuenta;
import views.components.NewAccountView;

import java.util.Random;

public class NewAccountController extends Controller {
    private NewAccountView newAccountView;

    private final BancoModel bancoModel;
    private final AccountModel accountModel;

    public NewAccountController(BancoModel bancoModel, AccountModel accountModel) {
        this.bancoModel = bancoModel;
        this.accountModel = accountModel;
    }

    @Override
    public void run() {
        newAccountView = new NewAccountView(this);

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

                Cuenta cuenta = new Cuenta.Builder()
                        .id(new Random(System.currentTimeMillis()).nextInt(1000))
                        .tipo(type)
                        .saldo(saldo)
                        .limite(limit)
                        .build();
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
