import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",plugin ="json:target/cucumber-report.json",glue = {"src/test/java/stepDefs"})
public class TestRunner {

    @Test
    public  void runCukes()
    {
        new TestNGCucumberRunner(getClass()).runCukes();
    }

}
