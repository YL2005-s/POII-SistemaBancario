package views.components;

import controllers.components.TransferenceController;
import entities.Cuenta;

import javax.swing.*;
import java.awt.*;

public class TransferenceView extends JPanel {
    private final TransferenceController transferenceController;

    private GridBagConstraints gbc;
    private JComboBox<Cuenta> cb_origin;
    private JComboBox<Cuenta> cb_destination;
    private JTextField tf_monto;
    private JButton btn_transfer;

    public TransferenceView(TransferenceController transferenceController) {
        this.transferenceController = transferenceController;

        make_frame();
        make_combo_origin();
        make_combo_destination();
        make_field_monto();
        make_btn_transfer();
    }

    private void make_frame() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void make_combo_origin() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Cuenta origen:"), gbc);

        gbc.gridx = 1;
        cb_origin = new JComboBox<>();
        cb_origin.setPreferredSize(new Dimension(225, 30));
        add(cb_origin, gbc);
    }

    private void make_combo_destination() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cuenta destino:"), gbc);

        gbc.gridx = 1;
        cb_destination = new JComboBox<>();
        cb_destination.setPreferredSize(new Dimension(225, 30));
        add(cb_destination, gbc);
    }

    private void make_field_monto() {
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Monto:"), gbc);

        gbc.gridx = 1;
        tf_monto = new JTextField();
        tf_monto.setPreferredSize(new Dimension(225, 30));
        add(tf_monto, gbc);
    }

    private void make_btn_transfer() {
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        btn_transfer = new JButton("Transferir");
        btn_transfer.setPreferredSize(new Dimension(160, 30));
        add(btn_transfer, gbc);
    }

    public JComboBox<Cuenta> getCb_origin() {
        return cb_origin;
    }

    public JComboBox<Cuenta> getCb_destination() {
        return cb_destination;
    }

    public JTextField getTf_monto() {
        return tf_monto;
    }

    public JButton getBtn_transfer() {
        return btn_transfer;
    }
}
