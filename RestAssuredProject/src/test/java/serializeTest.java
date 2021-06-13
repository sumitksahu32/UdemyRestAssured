import io.restassured.RestAssured;
import org.testng.annotations.Test;
import pojo.Location;
import pojo.addPlaceGoogleMaps;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class serializeTest {

@Test
public void doSerialize()
{

    addPlaceGoogleMaps apg = new addPlaceGoogleMaps();
    RestAssured.baseURI = "https://rahulshettyacademy.com/";
    List<String> ls = new ArrayList<>();
    ls.add("shoe park");
    ls.add("shop");
    Location lc = new Location();
    lc.setLat(34.3232323);
    lc.setLng(43.2343344);

    apg.setAccuracy(50);
    apg.setAddress("18 Park Street, LA");
    apg.setLanguage("EN");
    apg.setName("Jamie Lannistor");
    apg.setLocation(lc);
    apg.setTypes(ls);
    apg.setPhone_number("8989898989");
    apg.setWebsite("");

    String resText = given().log().all().queryParam("key","qaclick123").body(apg).when().post("/maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200).extract().response().asString();

    System.out.println(resText);

}



}
