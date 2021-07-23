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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrunkCase {
    private List<List<Integer>> allTrunks;
}
