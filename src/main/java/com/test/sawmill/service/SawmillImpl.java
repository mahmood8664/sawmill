package com.test.sawmill.service;

import com.test.sawmill.exception.APIException;
import com.test.sawmill.model.AggregateTrunkSolution;
import com.test.sawmill.model.TrunkCase;
import com.test.sawmill.model.TrunkSolution;
import com.test.sawmill.parser.FileParser;
import com.test.sawmill.util.PermutationUtil;
import com.test.sawmill.util.Stringifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
class SawmillImpl implements SawmillService {

    private final static int CUTTER_SIZE = 3;

    SawmillImpl() {
    }

    @Override
    public String solve(String filePath) {

        List<TrunkCase> trunkCases = FileParser.readCases(filePath);

        List<AggregateTrunkSolution> aggregateTrunkSolutions = new ArrayList<>();

        for (int i = 0; i < trunkCases.size(); i++) {
            TrunkCase trunkCase = trunkCases.get(i);

            List<TrunkSolution> solutions = new ArrayList<>();
            for (List<Integer> riversTrunk : trunkCase.getRiversTrunks()) {
                solutions.add(solveOne(riversTrunk));
            }

            aggregateTrunkSolutions.add(aggregate(solutions, i + 1));

        }

        return Stringifier.stringify(aggregateTrunkSolutions);
    }

    AggregateTrunkSolution aggregate(List<TrunkSolution> solutionList, int caseNumber) {

        int maxProfit = 0;

        AggregateTrunkSolution aggregateTrunkSolution = new AggregateTrunkSolution();
        aggregateTrunkSolution.setSolutions(new ArrayList<>());

        for (TrunkSolution trunkSolution : solutionList) {
            maxProfit += trunkSolution.getMaxProfit();
            aggregateTrunkSolution.getSolutions().add(trunkSolution.getSolutions());
        }
        aggregateTrunkSolution.setMaxProfit(maxProfit);
        aggregateTrunkSolution.setCaseNumber(caseNumber);

        return aggregateTrunkSolution;

    }


    TrunkSolution solveOne(List<Integer> trunks) {
        TrunkSolution solution = TrunkSolution.builder()
                .maxProfit(0)
                .solutions(new ArrayList<>())
                .build();

        for (List<Integer> oneRiver : solution.getSolutions()) {

            List<List<Integer>> allStates = PermutationUtil.generatePerm(oneRiver);
            for (List<Integer> aState : allStates) {
                calculateProfit(aState);
            }

        }

        return solution;

    }

    private int calculateProfit(List<Integer> aState) {
        int profit = 0;

        int sawIndex = 0;
        List<Integer> itemsAfterCut = new ArrayList<>();

        int remaining = 0;
        for (Integer trunk : aState) {

            int divide = trunk / CUTTER_SIZE;
            remaining = trunk % CUTTER_SIZE;


        }

        return profit;
    }

    int profit(int i) {
        switch (i) {
            case 1:
                return -1;
            case 2:
                return 3;
            case 3:
                return 1;
            default:
                throw new APIException("invalid cut length");
        }
    }

}
