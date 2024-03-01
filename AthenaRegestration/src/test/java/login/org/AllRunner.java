package login.org;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features/AthenaLogin.feature" }, glue = {
		"login" }, monochrome = false, plugin = { "pretty", "html:target/HtmlReports/reports.html", "pretty",
				"json:target/JSONReports/report.json", "pretty",
				"junit:target/JUNITReport/report.junit", })

public class AllRunner {

}
