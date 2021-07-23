package com.test.sawmill.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class PermutationUtil {

    public static <E> List<List<E>> generatePerm(List<E> original) {
        List<E> copy = new ArrayList<>(original);
        Collections.copy(copy, copy);
        if (copy.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = copy.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(copy);
        for (List<E> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

}
