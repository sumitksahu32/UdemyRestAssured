package stepDefs;

import impl.placeAPIImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class MyStepdefs {

    placeAPIImpl obj = new placeAPIImpl();

    @Given("^Add place payload$")
    public void addPlacePayload() throws FileNotFoundException {

        obj.addPlacePayload();
    }

    @Then("^status code is \"([^\"]*)\"$")
    public void statusCodeIs(String statusCode) throws Throwable {

        obj.verifyStatus(statusCode);
    }

    @When("user calls {string} with POST http request")
    public void userCallsWithPOSTHttpRequest(String arg0) {

        obj.addPlaceAPIWithPOST();
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String ele, String value) {

        obj.verifyResponse(ele,value);
    }
}
