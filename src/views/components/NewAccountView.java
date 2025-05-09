package views.components;

import controllers.components.NewAccountController;

import javax.swing.*;
import java.awt.*;

public class NewAccountView extends JPanel {
    private final NewAccountController newAccountController;

    private GridBagConstraints gbc;
    private JComboBox<String> cb_type;
    private JTextField tf_saldo;
    private JLabel lbl_limit;
    private JTextField tf_limit;
    private JButton btn_create;

    public NewAccountView(NewAccountController newAccountController) {
        this.newAccountController = newAccountController;

        make_frame();
        make_combo_type();
        make_field_saldo();
        make_field_limit();
        make_btn_create();
    }

    private void make_frame() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void make_combo_type() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tipo de cuenta:"), gbc);

        gbc.gridx = 1;
        cb_type = new JComboBox<>(new String[]{"Ahorro", "Corriente", "Crédito"});
        add(cb_type, gbc);
    }

    private void make_field_saldo() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Saldo inicial:"), gbc);

        gbc.gridx = 1;
        tf_saldo = new JTextField();
        tf_saldo.setPreferredSize(new Dimension(225, 30));
        add(tf_saldo, gbc);
    }

    private void make_field_limit() {
        gbc.gridx = 0;
        gbc.gridy = 2;
        lbl_limit = new JLabel("Límite:");
        lbl_limit.setVisible(false);
        add(lbl_limit, gbc);

        gbc.gridx = 1;
        tf_limit = new JTextField();
        tf_limit.setVisible(false);
        tf_limit.setPreferredSize(new Dimension(225, 30));
        add(tf_limit, gbc);
    }

    private void make_btn_create() {
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        btn_create = new JButton("Crear");
        btn_create.setPreferredSize(new Dimension(200, 35));
        add(btn_create, gbc);
    }

    public JComboBox<String> getCb_type() {
        return cb_type;
    }

    public JTextField getTf_saldo() {
        return tf_saldo;
    }

    public JLabel getLbl_limit() {
        return lbl_limit;
    }

    public JTextField getTf_limit() {
        return tf_limit;
    }

    public JButton getBtn_create() {
        return btn_create;
    }
}
