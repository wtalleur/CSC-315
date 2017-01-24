package com.wtalleur.life;

import com.wtalleur.Constants;
import com.wtalleur.cell.Cell;
import com.wtalleur.cell.CellState;
import com.wtalleur.cell.Generations;
import com.wtalleur.shapes.ShapeSet;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 7, 2015
 */
public class LifePanel extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private static final long serialVersionUID = 5380663536279287973L;

    private static Cell[][] cells;
    private LifeGrid lifeGrid;
    private Thread lifeThread;
    private int generations;

    public LifePanel() {
        setLayout(new MigLayout("insets 20 20 20 20"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		/* Add mouse listeners for grid interaction */
        addMouseListener(this);
        addMouseMotionListener(this);

		/* Instantiate the grid and cells */
        if (lifeGrid == null)
            lifeGrid = new LifeGrid(72, 45);
        if (cells == null)
            cells = new Cell[lifeGrid.getWidth()][lifeGrid.getHeight()];

		/* The default generation speed and shape when the game starts */
        generations = Generations.SLOW.getSpeed();
        addShape(ShapeSet.GLIDER);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        /* Draw Cells */
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                Cell cell = cells[x][y];
                if (cell == null)
                    continue;

                if (cell.getState().equals(CellState.ALIVE)) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(Constants.GRID_POINT_SIZE + (Constants.GRID_POINT_SIZE * cell.getX()), Constants.GRID_POINT_SIZE + (Constants.GRID_POINT_SIZE * cell.getY()), Constants.GRID_POINT_SIZE, Constants.GRID_POINT_SIZE);
                }
            }
        }
		/* Draw Grid */
        g2d.setColor(Color.BLACK);
        for (int index = 0; index < lifeGrid.getWidth(); index++) {
            g2d.drawLine((index * Constants.GRID_POINT_SIZE) + Constants.GRID_POINT_SIZE, Constants.GRID_POINT_SIZE, (index * Constants.GRID_POINT_SIZE) + Constants.GRID_POINT_SIZE, Constants.GRID_POINT_SIZE + (Constants.GRID_POINT_SIZE * lifeGrid.getHeight()));
        }
        for (int index = 0; index < lifeGrid.getHeight() + 1; index++) {
            g2d.drawLine(Constants.GRID_POINT_SIZE, ((index * Constants.GRID_POINT_SIZE) + Constants.GRID_POINT_SIZE), Constants.GRID_POINT_SIZE * lifeGrid.getWidth(), ((index * Constants.GRID_POINT_SIZE) + Constants.GRID_POINT_SIZE));
        }
    }

    /**
     * Adds a cell to the grid, if there isn't one already there.
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     */
    public void addCell(int x, int y) {
        if (cells[x][y] == null) {
            cells[x][y] = new Cell(x, y, CellState.ALIVE);
        } else {
            if (Constants.DEBUG_MODE)
                System.err.println("Grid cell at (" + x + ", " + y + ") already contains a cell.");
        }
        repaint();
    }

    /**
     * Adds a cell to the grid based on a mouseEvent.
     *
     * @param mouseEvent The mouse event
     */
    public void addCell(MouseEvent mouseEvent) {
        int x = mouseEvent.getPoint().x / Constants.GRID_POINT_SIZE - 1;
        int y = mouseEvent.getPoint().y / Constants.GRID_POINT_SIZE - 1;
        if ((x >= 0 && x < lifeGrid.getWidth() - 1) && (y >= 0 && y < lifeGrid.getHeight())) {
            addCell(x, y);
        }
        if (Constants.DEBUG_MODE) {
            if ((x >= 0 && x < lifeGrid.getWidth() - 1) && (y >= 0 && y < lifeGrid.getHeight()))
                System.out.println("Clicked grid cell (" + x + ", " + y + ")");
        }
    }

    /**
     * Adds a predefined shape (xlection of cells) to the grid.
     *
     * @param shapeSet The shape to add
     */
    public void addShape(ShapeSet shapeSet) {
        resetCells(); // automatically clear the grid before adding a new shape
        for (int[] coordinates : shapeSet.getShape().getShapeCoordinates()) {
            int x = coordinates[0];
            int y = coordinates[1];
            addCell(x, y);
        }
    }

    /**
     * Resets/clears the cells on the grid.
     */
    public void resetCells() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                cells[x][y] = null;
            }
        }
    }

    /**
     * Used for reloading the cells from a saved game file.
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     */
    public static void reloadCells(int x, int y) {
        if (cells[x][y] == null) {
            cells[x][y] = new Cell(x, y, CellState.ALIVE);
        } else {
            if (Constants.DEBUG_MODE)
                System.err.println("Grid cell at (" + x + ", " + y + ") already contains a cell.");
        }
    }

    /* Unused mouse events */
    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
		/* Mouse was released (single click) */
        addCell(event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
		/* Mouse was clicked and held (drew several points) */
        addCell(event);
    }

    @Override
    public void run() {
        int width = lifeGrid.getWidth();
        int height = lifeGrid.getHeight();

		/* A grid boolean array to store which cells are active/alive */
        boolean[][] grid = new boolean[width + 2][height + 1];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = cells[x][y];
                if (cell == null)
                    continue;

                grid[cell.getX() + 1][cell.getY() + 1] = true;
            }
        }

        boolean wrappable = true;

		/* Neighbors, direction checking TODO: wrapping */
        for (int x = 1; x < grid.length - 1; x++) {
            for (int y = 1; y < grid[0].length - 1; y++) {
                int neighbors = 0;
                int down = x - 1;
                int up = x + 1;
                int left = y - 1;
                int right = y + 1;

                if (wrappable) {
                    if (down == -1)
                        down = grid.length - 1;
                    if (up == (grid.length))
                        up = 0;
                    if (left == -1)
                        left = grid[0].length - 1;
                    if (right == (grid[0].length))
                        right = 0;
                }

                if (grid[down][left]) // BOTTOM LEFT
                    neighbors++;
                if (grid[down][y]) // LEFT
                    neighbors++;
                if (grid[down][right]) // TOP LEFT
                    neighbors++;
                if (grid[x][left]) // BOTTOM
                    neighbors++;
                if (grid[x][right]) // TOP
                    neighbors++;
                if (grid[up][left]) // BOTTOM RIGHT
                    neighbors++;
                if (grid[up][y]) // RIGHT
                    neighbors++;
                if (grid[up][right]) // TOP RIGHT
                    neighbors++;

                if (grid[x][y]) {
					/*  Any live cell with two or three live neighbors lives on to the next generation */
                    if ((neighbors == 2) || (neighbors == 3)) {
                        if (cells[x - 1][y - 1] == null) {
                            cells[x - 1][y - 1] = new Cell(x - 1, y - 1, CellState.ALIVE);
                        }
                        if (Constants.DEBUG_MODE)
                            System.err.println("Cell at (" + (x - 1) + ", " + (y - 1) + ")");
                    } else {
						/* Cell has died, set it to null. TODO: Figure out how to set state to dead as well */
                        cells[x - 1][y - 1] = null;
                    }
                } else {
					/* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction */
                    if (neighbors == 3) {
                        if (cells[x - 1][y - 1] == null) {
                            cells[x - 1][y - 1] = new Cell(x - 1, y - 1, CellState.ALIVE);
                        }
                        if (Constants.DEBUG_MODE)
                            System.err.println("Cell at (" + (x - 1) + ", " + (y - 1) + ")");
                    } else {
						/* Cell has died, set it to null. TODO: Figure out how to set state to dead as well */
                        cells[x - 1][y - 1] = null;
                    }
                }
            }
        }
        repaint();
        while (isRunning()) {
            try {
                Thread.sleep(1000 / generations);
                run();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setGenerationSpeed(int speed) {
        this.generations = speed;
    }

    public void startLifeThread() {
        if (lifeThread == null) {
            lifeThread = new Thread(this);
            lifeThread.start();
        }
    }

    public void stopLifeThread() {
        lifeThread = null;
    }

    public boolean isRunning() {
        return lifeThread != null;
    }

    public LifeGrid getLifeGrid() {
        return lifeGrid;
    }

    public static Cell[][] getCells() {
        return cells;
    }
}