package org.leviatanplatform.sudoku.engine;

import org.leviatanplatform.sudoku.engine.util.SudokuUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SudokuResolver {

  public static Board solve(Board board) {
    board.validate();
    Board modBoard = board.copy();

    fillBoardWithOneItemInformation(modBoard);

    if (board.isEqual(modBoard)) {
      System.out.println("NO CHANGES MADE");
    } else {
      System.out.println("CHANGES MADE");
    }

    return modBoard;
  }

  private static void fillBoardWithOneItemInformation(Board board) {

    int maxNumber = board.getMaxNumber();

    for (int r = 0; r < maxNumber; r++) {
      for (int c = 0; c < maxNumber; c++) {
        Set<Integer> candidates = getPossibleCandidates(board, c, r);
        if (candidates.size() == 1) {
          board.set(c, r, candidates.iterator().next());
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

    // FIXME finish

    /*

    for (int br = 0; br < boardSizeSquare; br++) {

      for (int bc = 0; bc < boardSizeSquare; bc++) {

        List<Integer> listOfItems = new ArrayList<>();

        for (int r = 0; r < boardSizeSquare; r++) {
          for (int c = 0; c < boardSizeSquare; c++) {
            int row = boardSizeSquare * br + r;
            int column = boardSizeSquare * bc + c;
            listOfItems.add(board.get(column, row));
          }
        }
      }
    }

    */

    return null;
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


