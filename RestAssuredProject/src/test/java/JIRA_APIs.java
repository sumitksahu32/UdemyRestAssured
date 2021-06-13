import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JIRA_APIs {

    SessionFilter sf = new SessionFilter();

    @BeforeTest
    public void loginAPIForJIRA()
    {
        RestAssured.baseURI = "http://localhost:8080";
        sf = new SessionFilter();

        given().log().all().header("Content-Type","application/json")
                .body("{ \"username\": \"admin\", \"password\": \"admin\" }")
                .filter(sf)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200);

    }

    @Test
    public void addCommentToIssue()
    {

        String response =
                given().log().all().pathParam("id","10201").header("Content-Type","application/json")
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
        String response = given().log().all().pathParam("id","10100").header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data")
        .filter(sf).multiPart("file",new File("src/main/resources/addPlace.json"))
        .when().post("/rest/api/2/issue/{id}/attachments")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jPath = new JsonPath(response);
        String attID = jPath.getString("id");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Attachment ID is : " + attID);
    }

    @Test
    public void getIssue()
    {
        String issueDetails = given().log().all().pathParam("id","10201").queryParam("fields","comment")
                .filter(sf)
                .when().get("/rest/api/2/issue/{id}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();


    }

    @Test
    public void createIssue()
    {
        String issueIDResponse =
                given().log().all().header("Content-Type","application/json").filter(sf)
                .body("{\n" +
                        "  \"fields\": {\n" +
                        "    \"project\":\n" +
                        "     {\n" +
                        "      \"key\": \"SKS\"\n" +
                        "     },\n" +
                        "      \"summary\": \"ATM Card Defect\",\n" +
                        "      \"description\": \"Creating issues via Postman\",\n" +
                        "      \"issuetype\": \n" +
                        "      {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "      }\n" +
                        "  }\n" +
                        "}")
                .when().post("/rest/api/2/issue")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath jPath = new JsonPath(issueIDResponse);
        String issueID = jPath.get("id").toString();

        System.out.println("Created issue is : " + issueID);

    }
}
