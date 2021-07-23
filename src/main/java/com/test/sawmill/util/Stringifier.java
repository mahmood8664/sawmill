package com.test.sawmill.util;

import com.test.sawmill.model.AggregateTrunkSolution;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class Stringifier {

    public static String stringify(List<AggregateTrunkSolution> aggregateTrunkSolution) {

        StringBuilder output = new StringBuilder();
        for (AggregateTrunkSolution trunkSolution : aggregateTrunkSolution) {

            output.append("Case ").append(trunkSolution.getCaseNumber()).append("\n");
            output.append("Max profit: ").append(trunkSolution.getMaxProfit()).append("\n");
            output.append("Order: ");
            output.append(trunkSolution.getSolutions().stream().map(solutions ->
                            solutions.stream().map(
                                    aSolution ->
                                            "[".concat(aSolution.stream()
                                                    .map(Objects::toString)
                                                    .collect(Collectors.joining(" ")))
                                                    .concat("]")
                            ).collect(Collectors.joining(" "))
                    ).collect(Collectors.joining(",")).concat("\n")
            );

        }
        return output.toString();
    }

}
