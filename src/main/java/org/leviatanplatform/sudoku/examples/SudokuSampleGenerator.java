package org.leviatanplatform.sudoku.examples;

import org.leviatanplatform.sudoku.engine.Board;

public class SudokuSampleGenerator {

    public static Board getExampleBoardSize3Easy1() {
        return getExampleBoard(3, SIZE_3_EASY_1);
    }

    private static final Integer[][] SIZE_3_EASY_1 = new Integer[][] {
            { null,    8, null, null, null,    1,    6, null, null },
            { null,    7, null,    4, null, null, null,    2,    1 },
            {    5, null, null,    3,    9,    6, null, null, null },

            {    2, null,    4, null,    5, null,    1, null, null },
            { null, null,    8,    9, null,    7,    5, null, null },
            { null,    5,    7, null,    3, null,    9, null, null },

            { null, null, null,    5,    6,    3, null, null,    9 },
            {    3,    1, null, null, null,    2, null,    5, null },
            { null, null,    5,    8, null, null, null,    4, null },
    };

    private static Board getExampleBoard(int boardSizeSquare, Integer[][] invertedMatrix) {

        int maxNumber = boardSizeSquare * boardSizeSquare;
        Board board = new Board(boardSizeSquare);

        for (int r = 0; r < maxNumber; r++) {
            for (int c = 0; c < maxNumber; c++) {
                Integer value = invertedMatrix[maxNumber - 1 - r][c];
                board.set(c, r, value);
            }
        }

        return board;
    }
}
