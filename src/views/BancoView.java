package views;

import controllers.BancoController;
import core.Model;
import core.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BancoView extends JPanel implements View {
    private final BancoController bancoController;

    private JPanel navigationPanel;
    private JTextArea logArea;

    private JButton btn_create;
    private JButton btn_transfer;
    private JButton btn_movements;
    private JButton btn_exit;

    public BancoView(BancoController bancoController) {
        this.bancoController = bancoController;

        make_frame();
        make_navigationPanel();
        make_btns();
        make_logArea();
    }

    @Override
    public void update(Model model, Object data) {
        if (data instanceof String msg) {
            if (msg.startsWith("CLEAR:")) {
                logArea.setText(msg.substring(6) + "\n");
            } else {
                logArea.append(msg + "\n");
            }
        }
    }

    private void make_frame() {
        setLayout(new BorderLayout());
    }

    private void make_navigationPanel() {
        navigationPanel = new JPanel(new FlowLayout());
        add(navigationPanel, BorderLayout.NORTH);
    }

    private void make_btns() {
        btn_create = new JButton("1. Crear cuenta");
        btn_transfer = new JButton("2. Realizar transferencia");
        btn_movements = new JButton("3. Ver movimientos");
        btn_exit = new JButton("4. Salir");

        navigationPanel.add(btn_create);
        navigationPanel.add(btn_transfer);
        navigationPanel.add(btn_movements);
        navigationPanel.add(btn_exit);
    }

    private void make_logArea() {
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        logArea.setMargin(new Insets(10, 15, 10, 10));
        logArea.setBackground(new Color(245, 245, 245));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getBtn_movements() {
        return btn_movements;
    }

    public JButton getBtn_create() {
        return btn_create;
    }

    public JButton getBtn_exit() {
        return btn_exit;
    }

    public JButton getBtn_transfer() {
        return btn_transfer;
    }
}
