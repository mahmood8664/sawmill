package com.test.sawmill.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class PermutationUtilTest {

    @Test
    void PermutationTest() {
        List<List<Integer>> lists = PermutationUtil.generatePerm(List.of(1, 2));
        Assertions.assertThat(lists).isEqualTo(List.of(List.of(1, 2), List.of(2, 1)));
    }

}
