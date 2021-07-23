package com.test.sawmill.service;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class SawmillFactory {

    private SawmillFactory() {
    }

    private static final SawmillImpl instance = new SawmillImpl();

    public static SawmillService getInstance() {
        return instance;
    }

}
