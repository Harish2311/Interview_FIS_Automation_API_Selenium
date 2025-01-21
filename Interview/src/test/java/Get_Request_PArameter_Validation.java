import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.Set;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class Get_Request_PArameter_Validation {

    public static void main(String[] args){

        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/currentprice.json");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        String gbpDescription = body.jsonPath().getString("bpi.GBP.description");
        System.out.println(gbpDescription);
        Assert.assertEquals(gbpDescription,"British Pound Sterling");
        Set<Object> Currencies = body.jsonPath().getMap("bpi").keySet();
        System.out.println(Currencies);
        assertThat(Currencies,hasItems("USD", "GBP", "EUR"));


    }
}
