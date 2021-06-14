package stepDefs;

import data.APIResources;
import impl.placeAPIImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class MyStepdefs {

    placeAPIImpl obj = new placeAPIImpl();

    @Then("^status code is \"([^\"]*)\"$")
    public void statusCodeIs(String statusCode) throws Throwable {

        obj.verifyStatus(statusCode);
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithPOSTHttpRequest(String arg0,String method) throws Exception {

        APIResources apir = APIResources.valueOf(arg0);
        obj.addPlaceAPIWithPOST(apir.getResource(),method);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String ele, String value) {

        obj.verifyResponse(ele,value);
    }

    @Given("Add place payload with {string} {string} {string}")
    public void addPlacePayloadWith(String arg0, String arg1, String arg2) throws Exception {

        obj.addPlacePayload(arg0,arg1,arg2);
    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreteMapsToUsing(String exParam, String method) throws Exception {

        APIResources apir = APIResources.valueOf(method);
        obj.getPlaceAPIwithGet(exParam,apir.getResource());

    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws Exception {
        obj.deletePlacePayLoad();

    }
}
