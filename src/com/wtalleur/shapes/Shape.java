package com.wtalleur.shapes;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 19, 2015
 */
public class Shape {

    private String name;
    private int[][] shape;

    public Shape(String name, int[][] shape) {
        this.name = name;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public int[][] getShapeCoordinates() {
        return shape;
    }

}