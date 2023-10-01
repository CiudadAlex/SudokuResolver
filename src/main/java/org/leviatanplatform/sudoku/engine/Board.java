package org.leviatanplatform.sudoku.engine;

import org.leviatanplatform.sudoku.engine.validation.BoardValidationException;

public class Board {

    private final int boardSizeSquare;
    private final Integer[][] matrix;

    public Board(int boardSizeSquare) {

        this.boardSizeSquare = boardSizeSquare;
        int maxNumber = boardSizeSquare * boardSizeSquare;
        matrix = new Integer[maxNumber][maxNumber];
    }

    public Integer get(int column, int row) {
        return matrix[column][row];
    }

    public void set(int column, int row, Integer value) {
        this.matrix[column][row] = value;
    }

    public int getBoardSizeSquare() {
        return boardSizeSquare;
    }

    public int getMaxNumber() {
        return boardSizeSquare * boardSizeSquare;
    }

    public void validateSize() {

        int maxNumber = getMaxNumber();
        int dim1 = matrix.length;

        if (dim1 != maxNumber) {
            throw new BoardValidationException("Error in board dimension 1 is not " + maxNumber + ": " + dim1);
        }

        for (int i=0; i<maxNumber; i++) {

            int dim2 = matrix[i].length;

            if (dim2 != maxNumber) {
                throw new BoardValidationException("Error in board dimension 2 is not " + maxNumber + ": " + dim2);
            }
        }
    }

    public Board copy() {

        this.validateSize();

        int maxNumber = getMaxNumber();
        Board newBoard = new Board(boardSizeSquare);

        for (int r = 0; r < maxNumber; r++) {
            for (int c = 0; c < maxNumber; c++) {
                newBoard.set(c, r, matrix[c][r]);
            }
        }

        return newBoard;
    }
}
