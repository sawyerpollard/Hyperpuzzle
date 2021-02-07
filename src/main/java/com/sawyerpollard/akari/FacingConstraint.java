package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.Constraint;

import java.util.Stack;

public class FacingConstraint implements Constraint {
    private final AkariGame game;

    public FacingConstraint(AkariGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            Stack<String> stack = new Stack<>();

            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                String value = game.getValue(rowIndex, columnIndex);

                if (value.equals(AkariGame.LIGHT)) {
                    if (!stack.isEmpty() && stack.peek().equals(AkariGame.LIGHT)) return true;

                    stack.push(AkariGame.LIGHT);
                } else if (game.isBarrier(rowIndex, columnIndex)) {

                    if (!stack.isEmpty()) stack.pop();
                }
            }
            if (!stack.isEmpty()) stack.pop();
            if (!stack.isEmpty()) return true;
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            Stack<String> stack = new Stack<>();

            for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
                String value = game.getValue(rowIndex, columnIndex);

                if (value.equals(AkariGame.LIGHT)) {
                    if (!stack.isEmpty() && stack.peek().equals(AkariGame.LIGHT)) return true;

                    stack.push(AkariGame.LIGHT);
                } else if (game.isBarrier(rowIndex, columnIndex)) {

                    if (!stack.isEmpty()) stack.pop();
                }
            }
            if (!stack.isEmpty()) stack.pop();
            if (!stack.isEmpty()) return true;
        }

        return false;
    }

    @Override
    public boolean satisfied() {
        return !violated();
    }
}