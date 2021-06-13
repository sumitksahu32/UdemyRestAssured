package impl;

import data.testDataBuild;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.Location;
import pojo.addPlaceGoogleMaps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class placeAPIImpl {

    RequestSpecification reqS;
    ResponseSpecification respS;
    Response resp;
    testDataBuild tdb=new testDataBuild();

    public void addPlacePayload() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("log.txt"));
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(ps))
                .addFilter(ResponseLoggingFilter.logResponseTo(ps))
                .setContentType(ContentType.JSON).build();

        respS = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        reqS = given().spec(req).body(tdb.createPayLoad());

    }

    public void addPlaceAPIWithPOST()
    {
    resp = reqS.when().post("/maps/api/place/add/json").then().spec(respS).extract().response();
    }

    public void verifyResponse(String ele, String value)
    {
        String respText = resp.asString();
        JsonPath jPath = new JsonPath(respText);
        Assert.assertEquals(jPath.get(ele).toString(),value,"Response did not match");
    }

    public void verifyStatus(String statusCode)
    {
        Assert.assertEquals(resp.getStatusCode(),200,"Status code did not match");
    }

}
