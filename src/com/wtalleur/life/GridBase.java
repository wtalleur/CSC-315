package com.wtalleur.life;

public abstract class GridBase {

    protected int width;
    protected int height;
    protected int grid[][];

    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the number of cells in the width of the grid and resizes the grid keeping the currently state of cells.
     * If cells are "resized out of the grid" they are lost
     */
    public void setWidth(int width) {
        int newgrid[][] = new int[this.height][width];
        int w = Math.max(this.width, width);
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < w; ++j) {
                newgrid[i][j] = this.grid[i][j];
            }
        }
        this.width = width;
        this.grid = newgrid;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the number of cells in the height of the grid and resizes the grid keeping the currently state of cells.
     * If cells are "resized out of the grid" they are lost
     */
    public void setHeight(int height) {
        int newgrid[][] = new int[height][this.width];
        int h = Math.max(this.height, height);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < this.width; ++j) {
                newgrid[i][j] = this.grid[i][j];
            }
        }
        this.height = height;
        this.grid = newgrid;
    }

    /**
     * Returns the grid.
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid.
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Sets a grid cell at (x, y) to the specified value.
     */
    public abstract void setGridCell(int x, int y, int value);

    /**
     * Returns the grid cell value at (x, y)
     */
    public abstract int getGridCell(int x, int y);

    /**
     * Clears all cells in the grid.
     */
    public abstract void clear();

    /**
     * Clears all dead cells in the grid.
     */
    public abstract void clearDeadCells();

    /**
     * Loads a pattern from a file.
     *
     * @param filename The filename.
     * @param clear    Clear the pattern upon load?
     */
    public abstract void load(String filename, boolean clear);

    /**
     * Saves a pattern to a file.
     *
     * @param filename The filename.
     */
    public abstract void save(String filename);

}