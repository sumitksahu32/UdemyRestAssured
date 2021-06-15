package data;

import io.restassured.RestAssured;
import pojo.Location;
import pojo.addPlaceGoogleMaps;

import java.util.ArrayList;
import java.util.List;

public class testDataBuild {

    public addPlaceGoogleMaps createPayLoad(String name,String lang,String add)
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
        apg.setAddress(add);
        apg.setLanguage(lang);
        apg.setName(name);
        apg.setLocation(lc);
        apg.setTypes(ls);
        apg.setPhone_number("8989898989");
        apg.setWebsite("");

        return apg;
    }

    public String deleteAPIPayLoad(String placeID)
    {
        return "{\r\n\"place_id\":\"" + placeID + "\"\r\n}";
    }

    public static String addPlace()
    {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }
}
