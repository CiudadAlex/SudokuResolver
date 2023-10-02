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

  // FIXME use
  private static void fillBoardWithMultipleItemInformation(Board board) {

    int maxNumber = board.getMaxNumber();
    int boardSizeSquare = board.getBoardSizeSquare();

    for (int r = 0; r < maxNumber; r++) {
      fillBoardWithMultipleItemInformationRow(board, r);
    }

    for (int c = 0; c < maxNumber; c++) {
      fillBoardWithMultipleItemInformationColum(board, c);
    }

    for (int br = 0; br < boardSizeSquare; br++) {
      for (int bc = 0; bc < boardSizeSquare; bc++) {
        fillBoardWithMultipleItemInformationSquare(board, bc, br);
      }
    }
  }

  private static void fillBoardWithMultipleItemInformationSquare(Board board, int bc, int br) {

    int boardSizeSquare = board.getBoardSizeSquare();
    List<Position> listPosition = new ArrayList<>();

    for (int r = 0; r < boardSizeSquare; r++) {
      for (int c = 0; c < boardSizeSquare; c++) {
        int rowInSquare = boardSizeSquare * br + r;
        int columnInSquare = boardSizeSquare * bc + c;

        addToListPositionIfNull(board, columnInSquare, rowInSquare, listPosition);
      }
    }

    fillBoardWithMultipleItemInformation(board, listPosition);
  }

  private static void fillBoardWithMultipleItemInformationColum(Board board, int column) {

    int maxNumber = board.getMaxNumber();
    List<Position> listPosition = new ArrayList<>();

    for (int r = 0; r < maxNumber; r++) {
      addToListPositionIfNull(board, column, r, listPosition);
    }

    fillBoardWithMultipleItemInformation(board, listPosition);
  }

  private static void fillBoardWithMultipleItemInformationRow(Board board, int row) {

    int maxNumber = board.getMaxNumber();
    List<Position> listPosition = new ArrayList<>();

    for (int c = 0; c < maxNumber; c++) {
      addToListPositionIfNull(board, c, row, listPosition);
    }

    fillBoardWithMultipleItemInformation(board, listPosition);
  }

  private static void addToListPositionIfNull(Board board, int column, int row, List<Position> listPosition) {

    Integer value = board.get(column, row);

    if (value == null) {
      Set<Integer> candidates = getPossibleCandidates(board, column, row);
      Position position = new Position(column, row, candidates);
      listPosition.add(position);
    }
  }

  private static void fillBoardWithMultipleItemInformation(Board board, List<Position> listPosition) {

    int boardSizeSquare = board.getBoardSizeSquare();
    Set<Integer> setAllPossibleItems = SudokuUtils.createSetOfAllItems(boardSizeSquare);

    for (Integer item : setAllPossibleItems) {

      Position positionWithItem = findPositionWithUniqueItemAsCandidate(board, listPosition, item);

      if (positionWithItem != null) {
        board.set(positionWithItem.getColumn(), positionWithItem.getRow(), item);
      }
    }
  }

  private static Position findPositionWithUniqueItemAsCandidate(Board board, List<Position> listPosition, Integer item) {

    Position positionWithItem = null;
    int count = 0;

    for (Position position : listPosition) {
      if (position.getCandidates().contains(item)) {
        positionWithItem = position;
        count++;
      }
    }

    if (count == 1) {
      return positionWithItem;
    } else {
      return null;
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


