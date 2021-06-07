import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JIRA_APIs {

//    @BeforeTest
//    public void loginAPIForJIRA()
//    {
//        RestAssured.baseURI = "http://localhost:8080";
//        SessionFilter sf = new SessionFilter();
//
//        given().log().all().header("Content-Type","application/json")
//                .body("{ \"username\": \"admin\", \"password\": \"admin\" }")
//                .filter(sf)
//                .when().post("/rest/auth/1/session")
//                .then().log().all().assertThat().statusCode(200);
//
//    }

    @Test
    public void addCommentToIssue()
    {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter sf = new SessionFilter();

        given().log().all().header("Content-Type","application/json")
                .body("{ \"username\": \"admin\", \"password\": \"admin\" }")
                .filter(sf)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200);

        String response =
                given().log().all().pathParam("id","10003").header("Content-Type","application/json")
        .body("{\n" +
                "  \"visibility\": {\n" +
                "    \"type\": \"role\",\n" +
                "    \"value\": \"Administrators\"\n" +
                "  },\n" +
                "  \"body\": \"Added via RestAssured.\"\n" +
                "}")
        .filter(sf)
        .when().post("/rest/api/2/issue/{id}/comment")
        .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js = new JsonPath(response);
        String commentID = js.getString("id");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Comment ID is : " + commentID);

    }

    @Test
    public void addAttachmentToIssue()
    {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter sf = new SessionFilter();

        given().log().all().header("Content-Type","application/json")
                .body("{ \"username\": \"admin\", \"password\": \"admin\" }")
                .filter(sf)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200);

        ///rest/api/2/issue/{issueIdOrKey}/attachments

    }
}
