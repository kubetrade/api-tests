package com.kubetrade.test.api;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import javax.enterprise.context.ApplicationScoped;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

@ApplicationScoped
public class TestRunnerService {

    public String executeTestRun(String suiteName) {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        TestSuite testSuite = TestSuite.getTestSuiteByName(suiteName)
                .orElseThrow(() -> new TestSuiteNotFoundException(String.format("No suite found for name[%s]", suiteName)));
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(testSuite.getTestSuiteClass()))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        listener.getSummary().printTo(printWriter);
        return stringWriter.toString();
    }

}
