package com.test.sawmill.service;

import com.test.sawmill.exception.APIException;
import com.test.sawmill.model.AggregateTrunkSolution;
import com.test.sawmill.model.TrunkCase;
import com.test.sawmill.model.TrunkSolution;
import com.test.sawmill.parser.FileParser;
import com.test.sawmill.util.PermutationUtil;
import com.test.sawmill.util.Stringifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mahmood
 * @since 7/23/21
 */
class SawmillImpl implements SawmillService {

    private static final int CUTTER_SIZE = 3;

    SawmillImpl() {
    }

    @Override
    public String solve(String filePath) {
        List<TrunkCase> trunkCases = FileParser.readCases(filePath);
        List<AggregateTrunkSolution> aggregateTrunkSolutions = new ArrayList<>();

        for (int i = 0; i < trunkCases.size(); i++) {
            TrunkCase trunkCase = trunkCases.get(i);
            List<TrunkSolution> solutions = new ArrayList<>();
            for (List<Integer> riversTrunk : trunkCase.getAllTrunks()) {
                solutions.add(solveOne(riversTrunk));
            }
            aggregateTrunkSolutions.add(aggregate(solutions, i + 1));
        }

        return Stringifier.stringify(aggregateTrunkSolutions);
    }

    TrunkSolution solveOne(List<Integer> trunks) {
        List<List<Integer>> allStates = PermutationUtil.generatePerm(trunks);
        List<TrunkSolutionHolder> solutionHolders = new ArrayList<>();

        for (List<Integer> aState : allStates) {
            solutionHolders.add(TrunkSolutionHolder.builder()
                    .profit(calculateProfit(aState))
                    .order(aState)
                    .build());
        }

        List<TrunkSolutionHolder> maxHolders = keepMax(solutionHolders);

        return TrunkSolution.builder()
                .maxProfit(maxHolders.get(0).profit)
                .solutions(maxHolders.stream().map(TrunkSolutionHolder::getOrder).collect(Collectors.toList()))
                .build();

    }

    /**
     * Calculate profit of one order
     *
     * @param aState trunks
     * @return profit
     */
    int calculateProfit(List<Integer> aState) {
        int profit = 0;
        int prevRemaining = 0;
        for (Integer trunk : aState) {

            int divide = (trunk + prevRemaining) / CUTTER_SIZE;
            int remaining = (trunk + prevRemaining) % CUTTER_SIZE;

            if (divide == 0) {//trunk + prevRemaining < 3
                profit += profit(remaining - prevRemaining);
            } else {//trunk + prevRemaining >= 3
                profit += profit(3 - prevRemaining);
                profit += profit(remaining);
                profit += profit(3) * (divide - 1);
            }

            prevRemaining = remaining;
        }
        return profit;
    }

    int profit(int i) {
        switch (i) {
            case 0:
                return 0;
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

    /**
     * just keeps max profits
     */
    List<TrunkSolutionHolder> keepMax(List<TrunkSolutionHolder> solutionHolders) {
        List<TrunkSolutionHolder> copy = new ArrayList<>(solutionHolders);
        copy.sort((o1, o2) -> o2.profit - o1.profit);
        Set<TrunkSolutionHolder> newList = new HashSet<>();//define set to remove duplicate
        if (copy.size() <= 1) {
            return copy;
        } else {
            newList.add(copy.get(0));
            for (int i = 1; i < copy.size(); i++) {
                if (copy.get(i).profit == copy.get(i - 1).profit) {
                    newList.add(copy.get(i));
                } else {
                    break;
                }
            }
        }

        ArrayList<TrunkSolutionHolder> maxProfitList = new ArrayList<>(newList);
        maxProfitList.sort((o1, o2) -> compareList(o1.order, o2.getOrder()));
        return maxProfitList;
    }

    int compareList(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) {
            return l1.size() - l2.size();
        }
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                return l1.get(i) - l2.get(i);
            }
        }
        return 0;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class TrunkSolutionHolder {
        private int profit;
        private List<Integer> order;
    }
}
