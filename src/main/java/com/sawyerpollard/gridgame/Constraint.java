package com.sawyerpollard.gridgame;

public interface Constraint {
    boolean violated();

    boolean satisfied();
}
