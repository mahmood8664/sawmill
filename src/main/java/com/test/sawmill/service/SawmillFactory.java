package com.test.sawmill.service;

/**
 * @author mahmood
 * @since 7/23/21
 */
public class SawmillFactory {

    private static SawmillImpl instance;

    public static SawmillService getInstance() {
        if (instance == null) {
            synchronized (SawmillFactory.class) {
                if (instance == null) {
                    instance = new SawmillImpl();
                }
            }
        }
        return instance;
    }

}
