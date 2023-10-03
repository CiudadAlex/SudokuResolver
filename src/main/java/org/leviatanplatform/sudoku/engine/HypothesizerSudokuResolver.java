package org.leviatanplatform.sudoku.engine;

import org.leviatanplatform.sudoku.engine.validation.BoardValidationException;
import org.leviatanplatform.sudoku.engine.validation.BoardValidator;

import java.util.Set;

public class HypothesizerSudokuResolver {

    public static Board solve(Board board) {

        BoardValidator.validateBoard(board);

        if (board.isFinished()) {
            return board;
        }

        Board modBoard = SudokuResolver.solve(board);

        if (modBoard.isFinished()) {
            return modBoard;
        }

        Position positionWithLessCandidates = getPositionWithLessCandidates(modBoard);
        int column = positionWithLessCandidates.getColumn();
        int row = positionWithLessCandidates.getRow();

        for (Integer itemCandidate : positionWithLessCandidates.getCandidates()) {

            Board hypothesisBoard = modBoard.copy();
            hypothesisBoard.set(column, row, itemCandidate);

            try {
                hypothesisBoard = solve(hypothesisBoard);

                if (hypothesisBoard.isFinished()) {
                    return hypothesisBoard;
                }

            } catch(BoardValidationException e) {
                // Hypothesis failed
            }
        }

        throw new BoardValidationException("Run out of possible candidates");
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
