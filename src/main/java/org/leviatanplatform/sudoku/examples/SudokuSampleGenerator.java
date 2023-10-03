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

    public static Board getExampleBoardSize3Medium1() {
        return getExampleBoard(3, SIZE_3_MEDIUM_1);
    }

    private static final Integer[][] SIZE_3_MEDIUM_1 = new Integer[][] {
            { null, null, null,    1, null, null,    2,    6, null },
            {    7, null, null, null,    3, null, null, null, null },
            {    3, null,    2, null,    8, null,    4, null, null },

            { null, null, null,    4, null,    8, null, null,    1 },
            { null,    3,    5, null, null,    7,    9,    4, null },
            {    2, null, null,    3, null,    5, null, null, null },

            { null, null,    6, null,    5, null,    7, null,    9 },
            { null, null, null, null,    4, null, null, null,    8 },
            { null,    5,    7, null, null,    9, null, null, null },
    };

    public static Board getExampleBoardSize3Difficult1() {
        return getExampleBoard(3, SIZE_3_DIFFICULT_1);
    }

    private static final Integer[][] SIZE_3_DIFFICULT_1 = new Integer[][] {
            { null,    1, null, null, null,    8,    4, null,    7 },
            {    9,    5, null, null, null, null, null, null, null },
            { null, null,    8, null,    1, null, null, null, null },

            { null,    8,    2, null, null, null, null, null, null },
            {    7, null, null,    4, null,    6, null, null,    8 },
            { null, null, null, null, null, null,    6,    2, null },

            { null, null, null, null,    5, null,    7, null, null },
            { null, null, null, null, null, null, null,    8,    2 },
            {    5, null,    3,    2, null, null, null,    1, null },
    };

    public static Board getExampleBoardSize3Ultra1() {
        return getExampleBoard(3, SIZE_3_ULTRA_1);
    }

    private static final Integer[][] SIZE_3_ULTRA_1 = new Integer[][] {
            { null,    1, null, null, null,    8,    4, null,    7 },
            {    9,    5, null, null, null, null, null, null, null },
            { null, null,    8, null,    1, null, null, null, null },

            { null,    8,    2, null, null, null, null, null, null },
            {    7, null, null,    4, null, null, null, null,    8 },
            { null, null, null, null, null, null,    6,    2, null },

            { null, null, null, null,    5, null,    7, null, null },
            { null, null, null, null, null, null, null,    8,    2 },
            {    5, null,    3,    2, null, null, null,    1, null },
    };

    public static Board getExampleBoardSize4Easy1() {
        return getExampleBoard(4, SIZE_4_EASY_1);
    }

    private static final Integer[][] SIZE_4_EASY_1 = new Integer[][] {
            {   14, null,   11,    3,       2, null,    1,   16,       8,   13,    4, null,    null,    6, null,   15 },
            { null,    5,    2,   10,      11,   12, null, null,      14, null,    7,   15,       9, null,    3, null },
            { null,    9,    1, null,       7,   10,   13, null,      11,   12, null,    5,      14,    4, null,    8 },
            {   15,    7, null,   13,      14,    4,    5, null,       1,    6,    9,    2,    null,   10,   16, null },

            { null, null, null,    5,      12,   15, null, null,    null, null, null,    8,       3, null,    1,    2 },
            {    7, null, null,    6,       3,    2,    8,    5,      10, null,    1, null,      15,   12, null, null },
            {    2, null, null,   11,    null,    7, null, null,      13, null, null,    3,      16,    5, null,    6 },
            { null,    3, null, null,    null,    6,   16,   10,       2,    5,   11,   12,    null,    7,    9, null },

            {    3, null, null,   12,       9,    1,   15,   11,    null, null,    5, null,       6,    2,    7,   10 },
            {    5,   11,    6, null,      16, null, null,   14,       7,   15, null,    1,       4,    8, null,    3 },
            { null, null,   16, null,    null,    3, null,    7,    null,   10,   12, null,      11, null,   15, null },
            {   10,    2,    7,   15,       6, null, null,   12,       9,    3,   14, null,       5, null,   13,   16 },

            { null,    6, null,    7,      10,   11,    9,    2,    null,   14,   13, null,       1,   15,    8,   12 },
            {   11, null,    5,    4,    null,   14, null,    1,      12, null, null, null,      10, null,    6,    7 },
            { null,   13,   14, null,       8,    5, null,    6,      15,   11, null, null,       2,    3,    4, null },
            {    8, null,    9,    2,      15,   16,   12,    4,       3,    1, null, null,    null,   11, null,    5 },
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
