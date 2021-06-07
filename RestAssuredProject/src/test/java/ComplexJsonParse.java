import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String args[])
    {
        //mockAPI
        JsonPath jPath = new JsonPath(payLoad.coursePrice());
        int size = jPath.getInt("courses.size()");

        System.out.println("Total courses are : " + size);

        int totAmount = jPath.getInt("dashboard.purchaseAmount");
        System.out.println("Total amount is : " + totAmount);

        String firstTitle = jPath.getString("courses[0].title");
        System.out.println("First title is : " + firstTitle);

        int sumPrice=0;

        for(int i=0;i<size;i++)
        {
            String title = jPath.getString("courses[" + i + "].title");
            int price = jPath.getInt("courses[" + i + "].price");
            int copies = jPath.getInt("courses[" + i + "].copies");

            sumPrice += price * copies;

            System.out.println("Title : " + title + "\tPrice per copy : " + price );
        }

        for(int j=0;j<size;j++)
        {
            String title = jPath.getString("courses[" + j + "].title");
            if(title.equalsIgnoreCase("RPA"))
            {
                int copies = jPath.getInt("courses[" + j + "].copies");
                System.out.println("Title is : RPA and copies sold are : " + copies);
                break;
            }
        }

        Assert.assertEquals(sumPrice,totAmount,"Total price does not match");

    }
}
