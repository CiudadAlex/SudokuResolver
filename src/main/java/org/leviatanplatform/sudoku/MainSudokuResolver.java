package org.leviatanplatform.sudoku;

import org.leviatanplatform.sudoku.engine.Board;
import org.leviatanplatform.sudoku.engine.HypothesizerSudokuResolver;
import org.leviatanplatform.sudoku.examples.SudokuSampleGenerator;

public class MainSudokuResolver {

    public static void main(String[] args) {

        solve(SudokuSampleGenerator.getExampleBoardSize3Easy1());
        solve(SudokuSampleGenerator.getExampleBoardSize3Medium1());
        solve(SudokuSampleGenerator.getExampleBoardSize3Difficult1());
        solve(SudokuSampleGenerator.getExampleBoardSize3Ultra1());

        solve(SudokuSampleGenerator.getExampleBoardSize4Easy1());
    }

    private static void solve(Board board) {

        board.print();

        System.out.println("###################################################");

        Board solvedBoard = HypothesizerSudokuResolver.solve(board);
        solvedBoard.print();

        System.out.println("###################################################");
    }
}
