import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle)
    {
        RestAssured.baseURI = "http://216.10.245.166";

        String response =
                given().log().all().header("Content-Type","application/json")
                .body(payLoad.addBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jPath = new JsonPath(response);
        //String ID = jPath.getString("ID");
        //System.out.println("ID is : " + ID);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"cde","123"},{"def","234"},{"efg","345"}};
    }
}
