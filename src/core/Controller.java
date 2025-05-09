package core;

import javax.swing.*;
import java.awt.*;

public abstract class Controller {
    protected static final JFrame mainFrame = new JFrame("Sistema Bancario");
    private static final JPanel viewsViewer = new JPanel(new CardLayout());
    private static final JPanel formViewer = new JPanel(new CardLayout());

    static {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(viewsViewer, BorderLayout.CENTER);
        mainFrame.add(formViewer, BorderLayout.SOUTH);
    }
    public abstract void run();

    public static void addView(String viewName, Component view) {
        viewsViewer.add(view, viewName);
    }

    public static void addFormView(String viewName, Component view) {
        formViewer.add(view, viewName);
    }

    public static void loadView(String viewName) {
        CardLayout cl = (CardLayout) formViewer.getLayout();
        cl.show(formViewer, viewName);
    }
}
