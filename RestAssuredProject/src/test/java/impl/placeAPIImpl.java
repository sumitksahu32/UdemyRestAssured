package impl;

import data.Utils;
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

import java.io.*;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class placeAPIImpl extends Utils {

    RequestSpecification reqS;
    ResponseSpecification respS;
    Response resp;
    testDataBuild tdb=new testDataBuild();
    String placeID;
    JsonPath jPath;
    RequestSpecification req;

    public void addPlacePayload(String name, String lang,String add) throws Exception {

           reqS = given().spec(buildReqSpecification()).body(tdb.createPayLoad(name, lang, add));
    }

    public void addPlaceAPIWithPOST(String resource,String method) throws Exception
    {
        if(method.equalsIgnoreCase("POST"))
        {
            resp = reqS.when().post(resource).then().spec(buildResSpecification()).extract().response();
        }
        else if(method.equalsIgnoreCase("GET"))
        {
            resp = reqS.when().get(resource).then().spec(buildResSpecification()).extract().response();
        }
    }

    public void verifyResponse(String ele, String value)
    {
        String respText = resp.asString();
        jPath = new JsonPath(respText);
        Assert.assertEquals(jPath.get(ele).toString(),value,"Response did not match");
    }

    public void verifyStatus(String statusCode)
    {
        Assert.assertEquals(resp.getStatusCode(),200,"Status code did not match");
    }

    public void getPlaceAPIwithGet(String exParam,String resource) throws Exception
    {
        placeID = jPath.get("place_id").toString();
        reqS = given().spec(buildReqSpecification()).queryParam("place_id",placeID);
        addPlaceAPIWithPOST(resource,"GET");
        String respText = resp.asString();
        jPath = new JsonPath(respText);
        Assert.assertEquals(jPath.get("name").toString(),exParam,"Response did not match");
    }

    public void deletePlacePayLoad() throws Exception
    {
        reqS = given().spec(buildReqSpecification()).body("");

    }

}
