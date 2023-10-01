package org.leviatanplatform.sudoku.engine.util;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuUtils {

    public static Set<Integer> getUnseenItems(int boardSizeSquare, List<Integer> listOfItemsWithNulls) {

        List<Integer> listOfItems = listOfItemsWithNulls.stream().filter(Objects::nonNull).toList();

        Set<Integer> setOfUnseenItems = createSetOfAllItems(boardSizeSquare);

        for (Integer item : listOfItems) {
            setOfUnseenItems.remove(item);
        }

        return setOfUnseenItems;
    }

    public static Set<Integer> createSetOfAllItems(int boardSizeSquare) {

        int maxNumber = boardSizeSquare * boardSizeSquare;
        return new HashSet<>(IntStream.rangeClosed(1, maxNumber).boxed().toList());
    }
}
