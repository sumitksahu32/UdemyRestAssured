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

}
