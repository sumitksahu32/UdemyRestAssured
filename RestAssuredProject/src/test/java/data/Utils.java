package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public RequestSpecification buildReqSpecification() throws Exception {
        PrintStream ps = new PrintStream(new FileOutputStream("log.txt"));
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(ps))
                .addFilter(ResponseLoggingFilter.logResponseTo(ps))
                .setContentType(ContentType.JSON).build();

        return  req;

    }

    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/data/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public ResponseSpecification buildResSpecification() throws Exception
    {
        ResponseSpecification respS;
        respS = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        return respS;
    }


}
