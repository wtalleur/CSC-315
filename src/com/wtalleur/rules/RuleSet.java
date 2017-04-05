package com.wtalleur.rules;

import com.wtalleur.life.GridBase;

public interface RuleSet {

    void applyRule(GridBase grid);

    void setWrap(boolean wrap);

}