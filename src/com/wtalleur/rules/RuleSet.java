package com.wtalleur.rules;

import com.wtalleur.life.GridBase;

public interface RuleSet {

    public abstract void applyRule(GridBase grid);

    public abstract void setWrap(boolean wrap);

}