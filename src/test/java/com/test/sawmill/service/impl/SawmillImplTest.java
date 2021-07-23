package com.test.sawmill.service.impl;

import com.test.sawmill.service.SawmillFactory;
import com.test.sawmill.service.SawmillService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class SawmillImplTest {

    @Test
    void testSolve() {
        SawmillService service = SawmillFactory.getInstance();
        String solve = service.solve(Objects.requireNonNull(this.getClass().getResource("/example_input")).getPath());
        Assertions.assertThat(solve).isEqualTo("Case 1\n" +
                "Max profit: 4\n" +
                "Order:[1,3,2][2,3,1]\n" +
                "Case 2\n" +
                "Max profit: 8\n" +
                "Order:[1,2,1][2,1,1],[1,2][2,1],[1,4]");
    }

}
