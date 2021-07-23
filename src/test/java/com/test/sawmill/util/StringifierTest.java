package com.test.sawmill.util;

import com.test.sawmill.model.AggregateTrunkSolution;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
class StringifierTest {

    @Test
    void stringifierTest() {

        String stringify = Stringifier.stringify(List.of(
                AggregateTrunkSolution.builder()
                        .caseNumber(1)
                        .maxProfit(10)
                        .solutions(List.of(List.of(List.of(1, 2), List.of(2, 3)), List.of(List.of(4, 5), List.of(5, 6))))
                        .build(),
                AggregateTrunkSolution.builder()
                        .caseNumber(2)
                        .maxProfit(12)
                        .solutions(List.of(List.of(List.of(2), List.of(2, 5))))
                        .build()
                )
        );

        Assertions.assertThat(stringify).isEqualTo("Case 1\n" +
                "Max profit: 10\n" +
                "Order: [1 2] [2 3],[4 5] [5 6]\n" +
                "Case 2\n" +
                "Max profit: 12\n" +
                "Order: [2] [2 5]\n");

    }

}
