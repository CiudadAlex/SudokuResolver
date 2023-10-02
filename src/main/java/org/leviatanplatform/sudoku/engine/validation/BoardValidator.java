package org.leviatanplatform.sudoku.engine.validation;

import org.leviatanplatform.sudoku.engine.Board;
import org.leviatanplatform.sudoku.engine.util.SudokuUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoardValidator {

    public static void validateBoard(Board board) {

        board.validate();
        validateBoardItemsColumn(board);
        validateBoardItemsRow(board);
        validateBoardItemsSquare(board);
    }

    private static void validateBoardItemsSquare(Board board) {

        int boardSizeSquare = board.getBoardSizeSquare();

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

                validateListOfItems(board, "square [" + bc + ", " + br + "]", listOfItems);
            }
        }
    }

    private static void validateBoardItemsRow(Board board) {

        int maxNumber = board.getMaxNumber();

        for (int r = 0; r < maxNumber; r++) {

            List<Integer> listOfItems = new ArrayList<>();

            for (int c = 0; c < maxNumber; c++) {
                listOfItems.add(board.get(c,r));
            }

            validateListOfItems(board, "row [x, " + r + "]", listOfItems);
        }
    }

    private static void validateBoardItemsColumn(Board board) {

        int maxNumber = board.getMaxNumber();

        for (int c = 0; c < maxNumber; c++) {

            List<Integer> listOfItems = new ArrayList<>();

            for (int r = 0; r < maxNumber; r++) {
                listOfItems.add(board.get(c, r));
            }

            validateListOfItems(board, "colum [" + c + ", x]", listOfItems);
        }
    }

    private static void validateListOfItems(Board board, String name, List<Integer> listOfItemsWithNulls) {

        List<Integer> listOfItems = listOfItemsWithNulls.stream().filter(Objects::nonNull).toList();

        int boardSizeSquare = board.getBoardSizeSquare();
        Set<Integer> setOfAllItems = SudokuUtils.createSetOfAllItems(boardSizeSquare);

        for (Integer item : listOfItems) {

            boolean contained = setOfAllItems.remove(item);

            if (!contained) {
                throw new BoardValidationException("Error in board " + name + " has duplicated item: " + item
                        + " -> " + listOfItems);
            }
        }
    }



}