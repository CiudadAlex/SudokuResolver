package org.leviatanplatform.sudoku.engine;

import org.leviatanplatform.sudoku.engine.util.SudokuUtils;
import org.leviatanplatform.sudoku.engine.validation.BoardValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class SudokuResolver {

  public static Board solve(Board board) {

    BoardValidator.validateBoard(board);
    Board modBoard = board.copy();

    iterateUntilNoMoreChangesAreMade(modBoard, SudokuResolver::fillBoardWithOneItemInformation);

    BoardValidator.validateBoard(modBoard);

    return modBoard;
  }

  private static void iterateUntilNoMoreChangesAreMade(Board board, Consumer<Board> fillFunction) {

    BoardValidator.validateBoard(board);

    while (true) {

      Board boardBeforeFill = board.copy();
      fillFunction.accept(board);

      BoardValidator.validateBoard(board);

      if (boardBeforeFill.isEqual(board)) {
        break;
      }
    }
  }

  private static void fillBoardWithOneItemInformation(Board board) {

    int maxNumber = board.getMaxNumber();

    for (int r = 0; r < maxNumber; r++) {
      for (int c = 0; c < maxNumber; c++) {

        Integer value = board.get(c, r);

        if (value == null) {
          Set<Integer> candidates = getPossibleCandidates(board, c, r);
          if (candidates.size() == 1) {
            board.set(c, r, candidates.iterator().next());
          }
        }
      }
    }
  }

  private static Set<Integer> getPossibleCandidates(Board board, int column, int row) {

    Set<Integer> candidatesRow = getPossibleCandidatesRow(board, column, row);
    Set<Integer> candidatesColumn = getPossibleCandidatesColumn(board, column, row);
    Set<Integer> candidatesSquare = getPossibleCandidatesSquare(board, column, row);

    int boardSizeSquare = board.getBoardSizeSquare();
    Set<Integer> setAllPossibleItems = SudokuUtils.createSetOfAllItems(boardSizeSquare);

    Set<Integer> candidates = new HashSet<>();

    for (Integer item : setAllPossibleItems) {
      if (candidatesRow.contains(item) && candidatesColumn.contains(item) && candidatesSquare.contains(item)) {
        candidates.add(item);
      }
    }

    return candidates;
  }

  private static Set<Integer> getPossibleCandidatesSquare(Board board, int column, int row) {

    int boardSizeSquare = board.getBoardSizeSquare();
    int br = row / boardSizeSquare;
    int bc = column / boardSizeSquare;

    List<Integer> listOfItems = new ArrayList<>();

    for (int r = 0; r < boardSizeSquare; r++) {
      for (int c = 0; c < boardSizeSquare; c++) {
        int rowInSquare = boardSizeSquare * br + r;
        int columnInSquare = boardSizeSquare * bc + c;
        listOfItems.add(board.get(columnInSquare, rowInSquare));
      }
    }

    return SudokuUtils.getUnseenItems(boardSizeSquare, listOfItems);
  }

  private static Set<Integer> getPossibleCandidatesRow(Board board, int column, int row) {

    int boardSizeSquare = board.getBoardSizeSquare();

    int maxNumber = board.getMaxNumber();
    List<Integer> listOfItems = new ArrayList<>();

    for (int c = 0; c < maxNumber; c++) {
      listOfItems.add(board.get(c, row));
    }

    return SudokuUtils.getUnseenItems(boardSizeSquare, listOfItems);
  }

  private static Set<Integer> getPossibleCandidatesColumn(Board board, int column, int row) {

    int boardSizeSquare = board.getBoardSizeSquare();

    int maxNumber = board.getMaxNumber();
    List<Integer> listOfItems = new ArrayList<>();

    for (int r = 0; r < maxNumber; r++) {
      listOfItems.add(board.get(column, r));
    }

    return SudokuUtils.getUnseenItems(boardSizeSquare, listOfItems);
  }

}


