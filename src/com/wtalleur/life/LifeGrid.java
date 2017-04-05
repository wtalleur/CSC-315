package com.wtalleur.life;

import com.wtalleur.cell.Cell;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 7, 2015
 */
public class LifeGrid extends GridBase {

    private boolean wrappable = false;

    public LifeGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[this.width][this.height];
    }

    @Override
    public void setGridCell(int x, int y, int value) {
        if ((x >= 0) && (x < getWidth()) && (y >= 0) && (y < getHeight())) {
            this.grid[x][y] = value;
        }
    }

    @Override
    public int getGridCell(int x, int y) {
        return this.grid[x][y];
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearDeadCells() {

    }

    public boolean isWrappable() {
        return wrappable;
    }

    public void setWrappable(boolean wrap) {
        this.wrappable = wrap;
    }

    @Override
    public String toString() {
        return "Grid Constraints (" + this.getWidth() + ", " + this.getHeight() + ")";
    }

    @Override
    public void load(String filename, boolean clear) {
        File gameFile = new File("./resources/gamesaves/" + filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(gameFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                LifePanel.reloadCells(x, y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String filename) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss a");
        File gameFile = new File("./resources/gamesaves/" + dateFormat.format(date) + ".txt");
        if (!gameFile.exists()) {
            try {
                gameFile.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gameFile, false))) {
            Cell[][] cells = LifePanel.getCells();
            for (int x = 0; x < cells.length; x++) {
                for (int y = 0; y < cells[0].length; y++) {
                    Cell cell = cells[x][y];
                    if (cell == null)
                        continue;
                    writer.write(cell.getX() + "," + cell.getY() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}