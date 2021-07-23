package com.test.sawmill.parser;

import com.test.sawmill.model.TrunkCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class FileParserTest {

    @Test
    void testFileParser_Successful() {

        List<TrunkCase> trunkCases = FileParser.readCases(Objects.requireNonNull(this.getClass().getResource("/example_input")).getPath());

        Assertions.assertThat(trunkCases.size()).isEqualTo(2);

        Assertions.assertThat(trunkCases.get(0).getRiversTrunks()).isEqualTo(List.of(List.of(2, 3, 1)));

        Assertions.assertThat(trunkCases.get(1).getRiversTrunks()).isEqualTo(List.of(List.of(1, 2, 1), List.of(1, 2), List.of(1, 4)));

    }

}
