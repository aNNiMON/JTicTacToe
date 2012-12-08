package jtictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author aNNiMON
 */
public class TitlePanel extends JPanel {

    private boolean moveForm;
    private BufferedImage exitImage, exitImageBW;
    private BufferedImage minimizeImage, minimizeImageBW;
    private BufferedImage background;
    private Point clickedStart;
    private final JFrame mainFrame;
    private Font messageFont, infoFont;
    
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(480, 150));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        setLayout(null);

        exitButton.setIcon(new javax.swing.ImageIcon(exitImageBW));
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        add(exitButton);
        exitButton.setBounds(378, 2, 100, 50);

        minimizeButton.setIcon(new javax.swing.ImageIcon(minimizeImageBW));
        minimizeButton.setBorder(null);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseExited(evt);
            }
        });
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        add(minimizeButton);
        minimizeButton.setBounds(2, 2, 100, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        mainFrame.setVisible(false);
        mainFrame.dispose();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        mainFrame.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        exitButton.setIcon(new ImageIcon(exitImage));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setIcon(new ImageIcon(exitImageBW));
    }//GEN-LAST:event_exitButtonMouseExited

    private void minimizeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseEntered
        minimizeButton.setIcon(new ImageIcon(minimizeImage));
    }//GEN-LAST:event_minimizeButtonMouseEntered

    private void minimizeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseExited
        minimizeButton.setIcon(new ImageIcon(minimizeImageBW));
    }//GEN-LAST:event_minimizeButtonMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            moveForm = true;
            clickedStart = evt.getPoint();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        moveForm = false;
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (moveForm) {
            Point moved = evt.getLocationOnScreen();
            moved.translate(-clickedStart.x, -clickedStart.y);
            mainFrame.setLocation(moved);
        }
    }//GEN-LAST:event_formMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton minimizeButton;
    // End of variables declaration//GEN-END:variables

    
}
