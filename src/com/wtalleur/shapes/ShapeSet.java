package com.wtalleur.shapes;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 19, 2015
 */
public enum ShapeSet {

    GLIDER(new Shape("Glider", new int[][]{{37, 19}, {38, 19}, {39, 19}, {39, 20}, {38, 21}})),

    EXPLODER(new Shape("Exploder", new int[][]{{34, 20}, {34, 21}, {34, 22}, {34, 23}, {34, 24}, {36, 20}, {36, 24}, {38, 20}, {38, 21}, {38, 22}, {38, 23}, {38, 24}})),

    BLINKER(new Shape("Blinker", new int[][]{{36, 21}, {36, 20}, {36, 19}})),

    PULSAR(new Shape("Pulsar", new int[][]{{30, 22}, {31, 15}, {30, 15}, {32, 15}, {36, 15}, {37, 15}, {38, 15}, {33, 17}, {33, 18}, {33, 19}, {35, 17}, {35, 18}, {35, 19}, {36, 20}, {37, 20}, {38, 20}, {32, 20}, {31, 20}, {30, 20}, {31, 22}, {32, 22}, {36, 22}, {37, 22},
            {38, 22}, {35, 23}, {35, 24}, {35, 25}, {33, 23}, {33, 24}, {33, 25}, {32, 27}, {30, 27}, {31, 27}, {36, 27}, {37, 27}, {38, 27},
            {28, 19}, {28, 18}, {28, 17}, {28, 23}, {28, 24}, {28, 25}, {40, 19}, {40, 18}, {40, 17}, {40, 23}, {40, 24}, {40, 25}})),

    SPACESHIP(new Shape("Spaceship", new int[][]{{32, 19}, {31, 20}, {31, 21}, {31, 22}, {32, 22}, {33, 22}, {34, 22}, {35, 21}, {35, 19}}));

    private Shape shape;

    private ShapeSet(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

}