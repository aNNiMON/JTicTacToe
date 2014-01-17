package jtictactoe;

import java.util.Random;

/**
 *
 * @author aNNiMON
 */
public class Table {
    
    public static final int
            MODE_NOT_STRIKED = 0,
            MODE_STRIKE_HORIZONTAL = 0x04,
            MODE_STRIKE_VERTICAL = 0x08,
            MODE_STRIKE_DIAGONAL = 0x10,
            MODE_STRIKE_DIAGONAL_REVERSE = 0x20;
    
    private static final int TABLE_SIZE = 3;

    private final Figure[][] table;
    private final Random random;
    private int strikedMode;
    private boolean nextMoveIsX;
    
    public Table() {
        table = new Figure[TABLE_SIZE][TABLE_SIZE];
        random = new Random();
    }

    public void resetTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                table[i][j] = new Figure();
            }
        }
        strikedMode = MODE_NOT_STRIKED;
        nextMoveIsX = true;
    }

    public int getStrikedMode() {
        return strikedMode;
    }
    
    public Figure[][] getFiguresArray() {
        return table;
    }
    
    public void setFigure(int x, int y) {
        if ( (checkWinner() != Figure.EMPTY) || (!hasMoreMoves()) ) {
            resetTable();
            return;
        }
        
        boolean xIsNorm = ( (0 <= x) && (x < TABLE_SIZE) );
        boolean yIsNorm = ( (0 <= y) && (y < TABLE_SIZE) );
        if (xIsNorm && yIsNorm && table[y][x].isEmpty()) {
            table[y][x].setFigure(nextMoveIsX ? Figure.X : Figure.O);
            nextMoveIsX = !nextMoveIsX;
            computerMove();
        }
    }
    
    public void computerMove() {
        if ( (checkWinner() != Figure.EMPTY) || (!hasMoreMoves()) ) {
            //resetTable();
            return;
        }
        
        computerAI();
        nextMoveIsX = !nextMoveIsX;
    }
    
    private void computerAI() {
        final Figure[][] array = new Figure[][] {
            // Horizontal check
            table[0],
            table[1],
            table[2],
            
            // Vertical check
            { table[0][0], table[1][0], table[2][0] },
            { table[0][1], table[1][1], table[2][1] },
            { table[0][2], table[1][2], table[2][2] },
            
            // Diagonal check
            { table[0][0], table[1][1], table[2][2] },
            { table[0][2], table[1][1], table[2][0] }
        };
        final char[] maybeWinnerArray = new char[array.length];
        // Calculate winning situations for computer (zeroes).
        for (int i = 0; i < array.length; i++) {
            Figure[] figures = array[i];
            char maybeWinner = maybeWinnerComboIs(figures);
            maybeWinnerArray[i] = maybeWinner;
            if (maybeWinner == Figure.O) {
                if (setCompFigure(figures))  return;
            }
        }
        // Calculate winning situations for the player (crosses).
        for (int i = 0; i < array.length; i++) {
            Figure[] figures = array[i];
            char maybeWinner = maybeWinnerArray[i];
            if (maybeWinner == Figure.X) {
                if (setCompFigure(figures))  return;
            }
        }
        
        // Trying to put figure to the center.
        int x = 1, y = 1;
        if (!table[y][x].isEmpty() || (random.nextInt(5) <= 3) ) {
            // If it's busy, put to the corners.
            int count = 0;
            do {
                x = random.nextBoolean() ? 0 : 2;
                y = random.nextBoolean() ? 0 : 2;
                count++;
            } while ( (!table[y][x].isEmpty()) && (count < 10) );
            
            if (count > 9) {
                // Put to the remaining cells.
                int[][] pair = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
                count = 0;
                do {
                    x = pair[random.nextInt(pair.length)][0];
                    y = pair[random.nextInt(pair.length)][1];
                    count++;
                } while ( (!table[y][x].isEmpty()) && (count < 10) );
            }
        }
        table[y][x].setFigure(nextMoveIsX ? Figure.X : Figure.O);
    }
    
    public char checkWinner() {
        // Horizontal check
        for (int y = 0; y < TABLE_SIZE; y++) {
            if (isWinnerCombo(table[y][0], table[y][1], table[y][2])) {
                strikedMode = MODE_STRIKE_HORIZONTAL | y;
                return table[y][0].getFigure();
            }
        }
        // Vertical check
        for (int x = 0; x < TABLE_SIZE; x++) {
            if (isWinnerCombo(table[0][x], table[1][x], table[2][x])) {
                strikedMode = MODE_STRIKE_VERTICAL | x;
                return table[0][x].getFigure();
            }
        }
        // Diagonal check
        if (isWinnerCombo(table[0][0], table[1][1], table[2][2])) {
            strikedMode = MODE_STRIKE_DIAGONAL;
            return table[0][0].getFigure();
        }
        if (isWinnerCombo(table[0][2], table[1][1], table[2][0])) {
            strikedMode = MODE_STRIKE_DIAGONAL_REVERSE;
            return table[0][2].getFigure();
        }
        // No winner
        return Figure.EMPTY;
    }
    
    public boolean hasMoreMoves() {
        if (checkWinner() != Figure.EMPTY) return false;
        
        int emptyFiguresCount = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].isEmpty()) emptyFiguresCount++;
            }
        }
        
        return (emptyFiguresCount != 0);
    }
    
    private boolean isWinnerCombo(Figure f1, Figure f2, Figure f3) {
        if (f1.isEmpty()) return false;
        final char figure = f1.getFigure();
        return ( (figure == f2.getFigure()) &&
                 (figure == f3.getFigure()) );
    }
    
    private char maybeWinnerComboIs(Figure[] array) {
        int numX = 0, numO = 0;
        for (int i = 0; i < array.length; i++) {
            if (!array[i].isEmpty()) {
                if (array[i].getFigure() == Figure.X) numX++;
                else numO++;
            }
        }
        if (numX == 2) return Figure.X;
        if (numO == 2) return Figure.O;
        return Figure.EMPTY;
    }

    private boolean setCompFigure(Figure[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].isEmpty()) {
                array[i].setFigure(Figure.O);
                return true;
            }
        }
        return false;
    }
}