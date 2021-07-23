package com.test.sawmill.parser;

import com.test.sawmill.exception.APIException;
import com.test.sawmill.model.TrunkCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse and validate file data
 *
 * @author mahmood
 * @since 7/23/21
 */
public class FileParser {

    /**
     * Parser the file and return TrunkCases, never return null
     *
     * @param filePath file path
     * @return {@link List<TrunkCase>}
     */
    public static List<TrunkCase> readCases(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new APIException("File path is empty");
        }

        List<TrunkCase> trunkCaseList = new ArrayList<>();

        int line = 0;
        try (var reader = new BufferedReader(new FileReader(URLDecoder.decode(filePath, StandardCharsets.UTF_8)))) {
            line++;

            int numberOfNextLines = Integer.parseInt(reader.readLine());
            while( numberOfNextLines != 0){

                TrunkCase aCase = TrunkCase.builder().riversTrunks(new ArrayList<>()).build();

                for (int i = 0; i < numberOfNextLines; i++) {
                    List<Integer> trunks = new ArrayList<>();

                    String nextCaseString = reader.readLine();
                    String[] caseSplit = nextCaseString.split(" ");

                    int nextItem = Integer.parseInt(caseSplit[0]);
                    for (int j = 1; j <= nextItem; j++) {
                        trunks.add(Integer.valueOf(caseSplit[j]));
                    }
                    //
                    aCase.getRiversTrunks().add(trunks);
                }
                trunkCaseList.add(aCase);
                numberOfNextLines = Integer.parseInt(reader.readLine());
            }

        } catch (FileNotFoundException e) {
            throw new APIException("File not found -> " + filePath);
        } catch (IOException e) {
            throw new APIException("Cannot parser file, problem in line -> " + line, e);
        }
        return trunkCaseList;
    }

}
