package app;

import control.Controller2D;
import view.Window;

import javax.swing.*;

public class AppStart3 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            new Controller2D(window.getPanel());
            window.setVisible(true);
        });
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
    }

}
