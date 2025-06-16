package views.components;

import controllers.components.AccountsController;
import entities.Cuenta;

import javax.swing.*;
import java.awt.*;

public class AccountsView extends JPanel {
    private final AccountsController accountsController;

    private GridBagConstraints gbc;
    private JComboBox<Cuenta> cb_account;

    public AccountsView(AccountsController accountsController) {
        this.accountsController = accountsController;

        make_frame();
        make_combo_account();
    }

    private void make_frame() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void make_combo_account() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Elija la cuenta:"), gbc);

        gbc.gridx = 1;
        cb_account = new JComboBox<>();
        cb_account.setPreferredSize(new Dimension(250, 35));
        add(cb_account, gbc);
    }

    public JComboBox<Cuenta> getCb_account() {
        return cb_account;
    }
}
