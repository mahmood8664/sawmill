package com.test.sawmill.util;

import com.test.sawmill.model.AggregateTrunkSolution;
import com.test.sawmill.util.Stringifier;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class StringifierTest {

    @Test
    void stringifierTest(){
        //todo: complete this
        String stringify = Stringifier.stringify(List.of(AggregateTrunkSolution.builder().build()));

    }

}
