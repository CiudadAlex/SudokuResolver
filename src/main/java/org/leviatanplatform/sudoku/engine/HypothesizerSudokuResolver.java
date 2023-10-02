package org.leviatanplatform.sudoku.engine;

import java.util.Set;

public class HypothesizerSudokuResolver {

    public static Board solve(Board board) {

        Board modBoard = SudokuResolver.solve(board);

        if (!modBoard.isFinished()) {
            Position positionWithLessCandidates = getPositionWithLessCandidates(board);
            // FIXME select candidate and set it in copied board and iterate until finished or exception. If exception try an other candidate
        }

        // FIXME finish

        return modBoard;
    }

    private static Position getPositionWithLessCandidates(Board board) {

        int minNumCandidates = Integer.MAX_VALUE;
        Position positionWithLessCandidates = null;

        int maxNumber = board.getMaxNumber();

        for (int r = 0; r < maxNumber; r++) {
            for (int c = 0; c < maxNumber; c++) {

                Integer value = board.get(c, r);

                if (value == null) {
                    Set<Integer> candidates = SudokuResolver.getPossibleCandidates(board, c, r);
                    int numCandidates = candidates.size();

                    if (numCandidates < minNumCandidates) {
                        minNumCandidates = numCandidates;
                        positionWithLessCandidates = new Position(c, r, candidates);
                    }
                }
            }
        }

        return positionWithLessCandidates;
    }
}
