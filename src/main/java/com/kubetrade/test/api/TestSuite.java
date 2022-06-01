package com.kubetrade.test.api;

import com.kubetrade.test.api.marketdata.MarketDataSuite;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum TestSuite {

    MARKET_DATA("market-data", MarketDataSuite.class);

    private final String name;
    private final Class clazz;

    TestSuite(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Class getTestSuiteClass() {
        return clazz;
    }

    public static Optional<TestSuite> getTestSuiteByName(String name) {
        return Arrays.stream(TestSuite.values())
                .filter(testSuite -> Objects.equals(testSuite.name, name))
                .findFirst();
    }

}
