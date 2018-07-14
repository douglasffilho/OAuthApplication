package cucumberTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@ActiveProfiles({ "test", "local" })
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", tags = "~@Ignore", glue = "cucumberTest.features")
public class CucumberTest {

}
