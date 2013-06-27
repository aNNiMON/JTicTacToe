package jtictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author aNNiMON
 */
public class TitlePanel extends JPanel {
    
    private final BufferedImage exitImage, exitImageBW;
    private final BufferedImage minimizeImage, minimizeImageBW;
    private final BufferedImage background;
    private final JFrame mainFrame;
    private final Font messageFont, infoFont;

    private boolean moveForm;
    private Point clickedStart;
    
    private int winX, winO;
    private String message;
    
    public TitlePanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        background = Util.getImage("/res/title_bg.jpg");
        minimizeImage = Util.getImage("/res/minimize_button.png");
        minimizeImageBW = Util.getImage("/res/minimize_button_bw.png");
        exitImage = Util.getImage("/res/exit_button.png");
        exitImageBW = Util.getImage("/res/exit_button_bw.png");
        moveForm = false;
        messageFont = new Font("Arial", Font.BOLD, 60);
        infoFont = new Font("Arial", Font.PLAIN, 30);
        winX = winO = 0;
        message = "";
        initComponents();
    }
    
    public void updateWin(int winX, int winO) {
        this.winX += winX;
        this.winO += winO;
    }
    
    public void setMessage(String message) {
        this.message = message;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = background.getWidth();
        
        g.drawImage(background, 0, 0, null);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(infoFont);
        
        g.drawString("X:"+winX, 5, 110);
        String win = "O:" + winO;
        g.drawString(win, width - getFontMetrics(infoFont).stringWidth(win) - 5, 110);
        
        if (!message.isEmpty()) {
            g.setFont(messageFont);
            int x = getFontMetrics(messageFont).stringWidth(message) / 2;
            x = width / 2 - x;
            g.drawString(message, x, 110);
        }
    }
    
    private void initComponents() {
        setBackground(new Color(51, 51, 51));
        setPreferredSize(new Dimension(480, 150));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        setLayout(null);
        

        final JButton exitButton = new JButton();
        exitButton.setIcon(new ImageIcon(exitImageBW));
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                exitButton.setIcon(new ImageIcon(exitImage));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                exitButton.setIcon(new ImageIcon(exitImageBW));
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        add(exitButton);
        exitButton.setBounds(378, 2, 100, 50);

        final JButton minimizeButton = new JButton();
        minimizeButton.setIcon(new ImageIcon(minimizeImageBW));
        minimizeButton.setBorder(null);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                minimizeButton.setIcon(new ImageIcon(minimizeImage));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                minimizeButton.setIcon(new ImageIcon(minimizeImageBW));
            }
        });
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mainFrame.setState(Frame.ICONIFIED);
            }
        });
        add(minimizeButton);
        minimizeButton.setBounds(2, 2, 100, 50);
    }
    
    private void exitButtonActionPerformed(ActionEvent evt) {            
        mainFrame.setVisible(false);
        mainFrame.dispose();
        System.exit(0);
    }

    private void formMousePressed(MouseEvent evt) {   
        if (evt.getButton() == MouseEvent.BUTTON1) {
            moveForm = true;
            clickedStart = evt.getPoint();
        }
    }

    private void formMouseReleased(MouseEvent evt) {    
        moveForm = false;
    }

    private void formMouseDragged(MouseEvent evt) {   
        if (moveForm) {
            Point moved = evt.getLocationOnScreen();
            moved.translate(-clickedStart.x, -clickedStart.y);
            mainFrame.setLocation(moved);
        }
    }
    
}
