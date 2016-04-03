package Main;

import javax.swing.*;

/**
 * Created by Support on 02.04.2016.
 */
public class Game {
    public static void main(String[] args) {

        JFrame window = new JFrame("Game");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}
