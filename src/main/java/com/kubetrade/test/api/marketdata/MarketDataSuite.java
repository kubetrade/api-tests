package com.kubetrade.test.api.marketdata;

import com.kubetrade.test.api.Tags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.kubetrade.test.api.marketdata")
@IncludeTags(Tags.MARKET_DATA)
public class MarketDataSuite {
}
