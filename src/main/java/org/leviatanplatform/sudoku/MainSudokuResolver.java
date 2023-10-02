package org.leviatanplatform.sudoku;

import org.leviatanplatform.sudoku.engine.Board;
import org.leviatanplatform.sudoku.engine.SudokuResolver;
import org.leviatanplatform.sudoku.examples.SudokuSampleGenerator;

public class MainSudokuResolver {

    public static void main(String[] args) {

        Board board = SudokuSampleGenerator.getExampleBoardSize3Difficult1();
        board.print();

        System.out.println("###################################################");

        Board solvedBoard = SudokuResolver.solve(board);
        solvedBoard.print();
    }
}
