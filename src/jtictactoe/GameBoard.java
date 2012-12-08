package jtictactoe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author aNNiMON
 */
public class GameBoard extends JPanel {
    private static final int CELL_SIZE = 150;
    private static final int OFFSET = 15;
    
    private BufferedImage strikedHor, strikedVer, strikedDiag, strikedDiagRev;
    private BufferedImage background, figureX, figureO;
    private Table table;
    private TitlePanel title;
    private int imgWidth, imgHeight;

    public GameBoard(TitlePanel title) {
        this.title = title;
        initImages();
        imgWidth = background.getWidth();
        imgHeight = background.getHeight();
        setPreferredSize( new Dimension(imgWidth, imgHeight) );
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                gameBoardMousePressed(evt);
            }
        });
        table = new Table();
        table.resetTable();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        drawTable(g);
        drawStrike(g);
    }
    
    private void drawTable(Graphics g) {
        Figure[][] figures = table.getFiguresArray();
        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures[0].length; j++) {
                char figure = figures[i][j].getFigure();
                if (figure != Figure.EMPTY) {
                    BufferedImage image = ( (figure == Figure.X) ? figureX : figureO );
                    int x = j * CELL_SIZE + OFFSET + (CELL_SIZE - image.getWidth()) / 2;
                    int y = i * CELL_SIZE + OFFSET + (CELL_SIZE - image.getHeight()) / 2;
                    g.drawImage(image, x, y, null);
                }
            }
        }
    }

    private void drawStrike(Graphics g) {
        int strikeMode = table.getStrikedMode();
        if (strikeMode != Table.MODE_NOT_STRIKED) {
            final int strikedSize = strikedHor.getHeight();
            int coord = OFFSET + (CELL_SIZE - strikedSize) / 2;
            int strikePos = strikeMode & 0x03;
            
            if ( (strikeMode & Table.MODE_STRIKE_DIAGONAL) != 0 ) {
                g.drawImage(strikedDiag, coord, coord, null);
            } else if ( (strikeMode & Table.MODE_STRIKE_DIAGONAL_REVERSE) != 0 ) {
                g.drawImage(strikedDiagRev, coord - OFFSET / 2, coord - OFFSET / 2, null);
            } else if ( (strikeMode & Table.MODE_STRIKE_HORIZONTAL) != 0 ) {
                g.drawImage(strikedHor, coord, coord + strikePos * CELL_SIZE, null);
            } else if ( (strikeMode & Table.MODE_STRIKE_VERTICAL) != 0 ) {
                g.drawImage(strikedVer, coord + strikePos * CELL_SIZE, coord, null);
            }
        }
    }
    
    private void gameBoardMousePressed(MouseEvent evt) {
        Point id = convertToID( evt.getPoint() );
        //table.computerMove();
        table.setFigure(id.x, id.y);
        
        if (table.getStrikedMode() == Table.MODE_NOT_STRIKED) title.setMessage("");
        
        if (!table.hasMoreMoves()) {
            char winner = table.checkWinner();
            if (winner != Figure.EMPTY) {
                int winX = (winner == Figure.X) ? 1 : 0;
                title.updateWin(winX, 1 - winX);
                title.setMessage(winner + " is win");
            } else title.setMessage("Drow");
        }
        repaint();
    }
    
    private void initImages() {
        background = Util.getImage("/res/table_bg.jpg");
        figureX = Util.getImage("/res/x.png");
        figureO = Util.getImage("/res/o.png");
        strikedHor = Util.getImage("/res/striked.png");
        strikedVer = Util.getImage("/res/striked_ver.png");
        strikedDiag = Util.getImage("/res/striked_diagonal.png");
        strikedDiagRev = Util.getImage("/res/striked_diagonal_rev.png");
    }

    private Point convertToID(Point point) {
        point.x /= imgWidth / 3;
        point.y /= imgHeight / 3;
        
        return point;
    }
}
