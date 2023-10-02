package org.leviatanplatform.sudoku.engine;

import org.leviatanplatform.sudoku.engine.validation.BoardValidationException;

import java.util.Objects;

/**
 * [0][8]   ...    [8][8]
 *
 *  ...             ...
 *
 * [0][0]   ...    [8][0]
 *
 * -----------------------------------
 *
 * [0][maxNumber]   ...    [maxNumber][maxNumber]
 *
 *  ...             ...
 *
 * [0][0]           ...    [maxNumber][0]
 *
 */
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

    public void validate() {
        validateSize();
        validateItemsRange();
    }

    private void validateSize() {

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

    private void validateItemsRange() {

        int maxNumber = getMaxNumber();

        for (int c = 0; c < maxNumber; c++) {
            for (int r = 0; r < maxNumber; r++) {
                Integer value = get(c, r);
                if (value != null && (value <= 0 || value > maxNumber)) {
                    throw new BoardValidationException("Error in board item [" + c + ", " + r + "] = " + get(c, r));
                }
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

    public boolean isEqual(Board board) {

        if (boardSizeSquare != board.boardSizeSquare) {
            return false;
        }

        int maxNumber = getMaxNumber();

        for (int r = 0; r < maxNumber; r++) {
            for (int c = 0; c < maxNumber; c++) {
                if (!Objects.equals(get(c, r), board.get(c, r))) {
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {

        StringBuilder sb = new StringBuilder();
        int maxNumber = getMaxNumber();

        for (int r = maxNumber - 1; r >= 0; r--) {

            appendRepeating(sb, "_", maxNumber * 4 + 1);

            for (int c = 0; c < maxNumber; c++) {
                Integer value = get(c, r);
                String printableValue = transformItemForPrint(value);
                sb.append("| ").append(printableValue).append(" ");
            }

            sb.append("|\n");
        }

        appendRepeating(sb, "_", maxNumber * 4 + 1);
    }

    private void appendRepeating(StringBuilder sb, String txt, int numberRepeats) {

        for (int i = 0; i < numberRepeats; i++) {
            sb.append(txt);
        }
    }

    private String transformItemForPrint(Integer value) {

        String items = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        if (value == null) {
            return " ";
        }

        return items.length() > value ? "" + items.charAt(value) : "" + value;
    }
}
