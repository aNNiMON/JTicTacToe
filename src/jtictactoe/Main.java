package jtictactoe;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author aNNiMON
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        new Main().setVisible(true);
        
    }

    public Main() {
        super("JTicTacToe");
        setResizable(false);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBounds(300, 120, 0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        
        TitlePanel title = new TitlePanel(this);
        add(title, BorderLayout.NORTH);
        add(new GameBoard(title));
        
        pack();
    }
}
