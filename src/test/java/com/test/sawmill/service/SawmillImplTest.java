package com.test.sawmill.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author mahmood
 * @since 7/23/21
 */
class SawmillImplTest {

    @Test
    void testSolve() {
        SawmillService service = SawmillFactory.getInstance();
        String solve = service.solve(Objects.requireNonNull(this.getClass().getResource("/example_input")).getPath());
        Assertions.assertThat(solve).isEqualTo("Case 1\n" +
                "Max profit: 4\n" +
                "Order: [1 3 2] [2 3 1]\n" +
                "Case 2\n" +
                "Max profit: 8\n" +
                "Order: [1 2 1] [2 1 1],[1 2] [2 1],[1 4]\n");
    }


    @Test
    void calculateProfitTest() {
        SawmillImpl service = (SawmillImpl) SawmillFactory.getInstance();
        int profit = service.calculateProfit(List.of(3, 2, 1));
        Assertions.assertThat(profit).isEqualTo(3);

        profit = service.calculateProfit(List.of(3, 2, 1));
        Assertions.assertThat(profit).isEqualTo(3);

        profit = service.calculateProfit(List.of(1, 4, 5));
        Assertions.assertThat(profit).isEqualTo(4);

        profit = service.calculateProfit(List.of(1, 2, 3, 4, 5));
        Assertions.assertThat(profit).isEqualTo(7);

    }

    @Test
    void keepMaxTest() {
        SawmillImpl service = (SawmillImpl) SawmillFactory.getInstance();
        List<SawmillImpl.TrunkSolutionHolder> solutionHolders = service.keepMax(List.of(
                SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3)).profit(45).build(),
                SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3, 4)).profit(5).build(),
                SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3, 6)).profit(35).build(),
                SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3, 7)).profit(45).build()
        ));

        Assertions.assertThat(solutionHolders).isEqualTo(
                List.of(
                        SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3)).profit(45).build(),
                        SawmillImpl.TrunkSolutionHolder.builder().order(List.of(1, 2, 3, 7)).profit(45).build()
                )
        );
    }
}
