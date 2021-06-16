import impl.dataDriven;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    public static String bookID="";

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
        bookID = jPath.getString("ID");
        System.out.println(">>>>>>>>> bookID is : " + bookID);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"cde","123"},{"def","234"},{"efg","345"}};
    }

    @Test
    public void addBookPlain() throws Exception
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        dataDriven dd = new dataDriven();
        ArrayList arr = dd.doDDT();

        HashMap<String,Object> mp = new HashMap<>();
        mp.put("name",arr.get(0));
        mp.put("isbn",arr.get(1));
        mp.put("aisle",arr.get(2));
        mp.put("author",arr.get(3));

        String response = given().log().all().header("Content-Type","application/json")
                .body(mp)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jPath = new JsonPath(response);
        bookID = jPath.getString("ID");
        System.out.println(">>>>>>>>> bookID is : " + bookID);

        given().log().all().queryParam("ID",bookID)
                .when().get("/Library/GetBook.php")
                .then().log().all().assertThat().statusCode(200);
    }

}
