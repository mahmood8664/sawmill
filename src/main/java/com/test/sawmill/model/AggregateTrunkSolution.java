package com.test.sawmill.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mahmood
 * @since 7/23/21
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AggregateTrunkSolution {
    protected int maxProfit;
    protected List<List<List<Integer>>> solutions;
    private int caseNumber;
}
