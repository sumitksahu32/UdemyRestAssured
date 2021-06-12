import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pojo.API;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    @Test
    public void getResource() throws InterruptedException {
        RestAssured.baseURI = "";
        SessionFilter sf = new SessionFilter();

    System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.get("https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&flowName=GeneralOAuthFlow");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement email = driver.findElement(By.xpath("//input[@type='email' and @class='whsOnd zHQkBf']"));
    email.clear();
    email.sendKeys("verito.john@gmail.com");
    WebElement nextButton = driver.findElement(By.xpath("//span[@jsname='V67aGc' and @class='VfPpkd-vQzf8d' and contains(text(),'Next')]"));
    nextButton.click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement pwdTextBox = driver.findElement(By.xpath("//input[@type='password' and @class='whsOnd zHQkBf']"));
    pwdTextBox.clear();
    pwdTextBox.sendKeys("q1w2e3r4t5**");
    nextButton.click();
    Thread.sleep(4000);
    //String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g40CR8pd1znpHVCWpNZXZYxO88qVcfrSrKHKcxcQg4R6JaUPPN4qGdbbP6ghjMjRA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none#";
    String url = driver.getCurrentUrl();
    String temp  = url.split("code=")[1];
    String codeText = temp.split("&scope")[0];

    System.out.println(">>>>>>>> Auth code is : "+ codeText);

        String accessTokenResponse=
                given().log().all().urlEncodingEnabled(false).relaxedHTTPSValidation()
                .queryParam("code",codeText)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jPath = new JsonPath(accessTokenResponse);
        String accessToken = jPath.getString("access_token");

        System.out.println(">>>>>>>>>> Access token is : " + accessToken);

        String resource=
                given().log().all().relaxedHTTPSValidation().queryParam("access_token",accessToken)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        GetCourse gc =
                given().log().all().relaxedHTTPSValidation().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                        .when().get("https://rahulshettyacademy.com/getCourse.php")
                        .as(GetCourse.class);

        //System.out.println(">>>>>>>>>>> Resource is : " + resource);
        System.out.println(">>>>>>>>>>> Resource is : " + gc.getLinkedIn());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
        List<API> ls = gc.getCourses().getApi();
        for(int i=0;i<ls.size();i++)
        {
            String str = gc.getCourses().getApi().get(i).getCourseTitle();
            if(str.equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println("Price is " + gc.getCourses().getApi().get(i).getPrice());
            }
        }

        List<WebAutomation> lsWeb = gc.getCourses().getWebAutomation();
        for(int i=0;i<lsWeb.size();i++)
        {
            System.out.println(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
        }
    }
}
