package com.opencampus.automation.bases;

import com.opencampus.automation.core.ContextManager;
import com.opencampus.automation.core.Global;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.util.HashMap;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber/cucumber.json",
                "html:target/cucumber/cucumber-html-report.html",
                "rerun:target/cucumber/failed_scenarios.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }, monochrome = true)
public class BaseRunner {
    private static TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void setUp() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod
    public void initScenario() {
        Global.setThreadLocalMap(new HashMap<>());
        Global.set(ContextManager.class, new ContextManager());
    }

    @Test(dataProvider = "scenarios")
    public void runScenarios(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass
    public void tearDown() {
        testNGCucumberRunner.finish();
    }
}
