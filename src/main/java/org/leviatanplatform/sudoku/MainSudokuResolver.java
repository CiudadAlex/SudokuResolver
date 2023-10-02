package org.leviatanplatform.sudoku;

import org.leviatanplatform.sudoku.engine.Board;
import org.leviatanplatform.sudoku.engine.HypothesizerSudokuResolver;
import org.leviatanplatform.sudoku.examples.SudokuSampleGenerator;

public class MainSudokuResolver {

    public static void main(String[] args) {

        Board board = SudokuSampleGenerator.getExampleBoardSize3Ultra1();
        board.print();

        System.out.println("###################################################");

        Board solvedBoard = HypothesizerSudokuResolver.solve(board);
        solvedBoard.print();
    }
}
