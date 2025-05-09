package views.components;

import controllers.components.MovementsController;
import models.Cuenta;

import javax.swing.*;
import java.awt.*;

public class MovementsView extends JPanel {
    private final MovementsController movementsController;

    private GridBagConstraints gbc;
    private JComboBox<Cuenta> cb_account;
    private JButton btn_show;

    public MovementsView(MovementsController movementsController) {
        this.movementsController = movementsController;

        make_frame();
        make_combo_account();
        make_btn_show();
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
        add(new JLabel("Seleccione cuenta:"), gbc);

        gbc.gridx = 1;
        cb_account = new JComboBox<>();
        cb_account.setPreferredSize(new Dimension(225, 30));
        add(cb_account, gbc);
    }

    private void make_btn_show() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        btn_show = new JButton("Mostrar Movimientos");
        btn_show.setPreferredSize(new Dimension(200, 35));
        add(btn_show, gbc);
    }

    public JButton getBtn_show() {
        return btn_show;
    }

    public JComboBox<Cuenta> getCb_account() {
        return cb_account;
    }
}
