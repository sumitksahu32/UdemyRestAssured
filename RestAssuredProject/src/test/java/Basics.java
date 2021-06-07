import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args)
    {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add a Place
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
        .when().post("/maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)"));

        //Add Place -> Update Place with new Address -> Get Place to validate if new address present in response
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();

        System.out.println(">>>>>>>>>>>Response is : " + response);
        JsonPath jPath = new JsonPath(response);
        String placeID = jPath.getString("place_id");

        System.out.println(">>>>>>>>>>>>>>>Extracted Place ID is: " + placeID);

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeID + "\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
        .when().put("/maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
        .when().get("/maps/api/place/get/json")
        .then().log().all().statusCode(200).body("address",equalTo("70 Summer walk, USA"));

    }
}
