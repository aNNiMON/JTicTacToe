package jtictactoe;

/**
 *
 * @author aNNiMON
 */
public class Figure {
    
    public static final char
            EMPTY = ' ',
            X = 'X',
            O = 'O';
    
    protected char figure;

    public Figure() {
        this.figure = EMPTY;
    }
    
    public boolean isEmpty() {
        return (figure == EMPTY);
    }

    public char getFigure() {
        return figure;
    }

    public void setFigure(char figure) {
        this.figure = figure;
    }
}
