package org.leviatanplatform.sudoku.engine.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuUtils {

    public static Set<Integer> getUnseenItems(int boardSizeSquare, List<Integer> listOfItems) {

        Set<Integer> setOfUnseenItems = createSetOfAllItems(boardSizeSquare);

        for (Integer item : listOfItems) {
            setOfUnseenItems.remove(item);
        }

        return setOfUnseenItems;
    }


    private static Set<Integer> createSetOfAllItems(int boardSizeSquare) {

        int maxNumber = boardSizeSquare * boardSizeSquare;
        return new HashSet<>(IntStream.rangeClosed(1, maxNumber).boxed().toList());
    }
}
