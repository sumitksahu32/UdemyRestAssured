package data;

public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/add/json");

    private String resource;

    APIResources(String resource) {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
