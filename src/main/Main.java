package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel(window);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        String key = "ESCAPE";
        Object ref = null;
        Consumer<Object> action = (Object e) -> gamePanel.killGame();
        JComponent comp = window.getRootPane();
        KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
        String actionName = "action_" + key;

        comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(keyStroke, actionName);

        comp.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.accept(ref);
            }
        });
    }
}
