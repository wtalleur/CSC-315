package com.wtalleur.cell;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 13, 2015
 */
public class Cell {

    private CellState state = CellState.ALIVE;
    private int x;
    private int y;

    public Cell(int x, int y, CellState state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}