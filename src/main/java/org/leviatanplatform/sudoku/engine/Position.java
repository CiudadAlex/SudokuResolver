package org.leviatanplatform.sudoku.engine;

import java.util.Set;

public class Position {

    private final int column;
    private final int row;
    private final Set<Integer> candidates;

    public Position(int column, int row, Set<Integer> candidates) {
        this.column = column;
        this.row = row;
        this.candidates = candidates;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Set<Integer> getCandidates() {
        return candidates;
    }

}
