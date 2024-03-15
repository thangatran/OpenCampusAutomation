import com.opencampus.automation.bases.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        tags = "@api_regression")
public class ApiTestRunner extends BaseRunner {
}
