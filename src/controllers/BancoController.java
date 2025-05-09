package controllers;

import controllers.components.MovementsController;
import controllers.components.NewAccountController;
import controllers.components.TransferenceController;
import core.Controller;
import models.AccountModel;
import models.BancoModel;
import models.MovementModel;
import views.BancoView;
import views.components.MovementsView;
import views.components.NewAccountView;
import views.components.TransferenceView;

/**
 * Controlador principal, encargado de presentar la primera vista, también conocido como 'HomeView'
 */
public class BancoController extends Controller {
    private final BancoView bancoView = new BancoView(this);
    private final BancoModel bancoModel = new BancoModel();
    private final AccountModel accountModel = new AccountModel();
    private final MovementModel movementModel = new MovementModel();

    private final NewAccountController newAccountController = new NewAccountController(bancoModel, accountModel);
    private final TransferenceController transferenceController = new TransferenceController(bancoModel, accountModel, movementModel);
    private final MovementsController movementsController = new MovementsController(bancoModel, accountModel, movementModel);

    @Override
    public void run() {
        loadAllAccounts();
        loadAllMovements();

        newAccountController.run();
        transferenceController.run();
        movementsController.run();

        bancoModel.attach(bancoView);

        addView("BancoView", bancoView);
        addFormView("NewAccountView", getNewAccountView());
        addFormView("TransferenceView", getTransferenceView());
        addFormView("MovementsView", getMovementsView());

        mainFrame.setVisible(true);

        setupViewButtons();
    }

    private void loadAllAccounts() {
        String notice = accountModel.loadAccounts();
        bancoModel.log(notice);
    }

    private void loadAllMovements() {
        String notice = movementModel.loadMovements();
        bancoModel.log(notice);
    }

    private void setupViewButtons() {
        bancoView.getBtn_create().addActionListener(e -> {
            loadView("NewAccountView");
            bancoModel.log("CLEAR:");
        });

        bancoView.getBtn_transfer().addActionListener(e -> {
            loadView("TransferenceView");
            transferenceController.refreshComboBoxes();
            bancoModel.log("CLEAR:");
        });

        bancoView.getBtn_movements().addActionListener(e -> {
            loadView("MovementsView");
            movementsController.refreshComboBoxes();
            bancoModel.log("CLEAR:");
        });

        bancoView.getBtn_exit().addActionListener(e -> {
            System.exit(0);
        });
    }

    public NewAccountView getNewAccountView() {
        return newAccountController.getView();
    }

    public TransferenceView getTransferenceView() {
        return transferenceController.getView();
    }

    public MovementsView getMovementsView() {
        return movementsController.getView();
    }
}
