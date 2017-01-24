package com.wtalleur.cell;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 20, 2015
 */
public enum Generations {

    SLOW, FAST, HYPER;

    public int getSpeed() {
        return (this.ordinal() == 0 ? 5 : (this.ordinal() + 1) * 8);
    }
}