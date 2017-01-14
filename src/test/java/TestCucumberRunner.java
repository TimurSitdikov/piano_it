import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(tags = {"@SearchConcreteCompany,@SearchLocationProposals"},format = {"pretty", "json:target/cucumber.json","html:target/cucumber.html"}, features = {"src/main/resources/features/"})
public class TestCucumberRunner extends AbstractTestNGCucumberTests {
}
