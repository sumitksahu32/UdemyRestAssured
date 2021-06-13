package pojo;

public class GetCourse {

    private String url;
    private String expertise;
    private String instructor;
    private String linkedIn;
    private Courses Courses;

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    private String services;

    public pojo.Courses getCourses() {
        return Courses;
    }

    public void setCourses(pojo.Courses courses) {
        Courses = courses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }


}
