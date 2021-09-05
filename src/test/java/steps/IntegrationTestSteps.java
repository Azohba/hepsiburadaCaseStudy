package steps;

import com.thoughtworks.gauge.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class IntegrationTestSteps {

    private static Logger log = Logger.getLogger(IntegrationTestSteps.class.getName());
    public static Response response;

    @Step("I perform GET operation to call <http://generator.swagger.io/api/swagger.json> API URL")
    public void getSwagger(String endpoint) {
        response = given().
                contentType(ContentType.JSON).relaxedHTTPSValidation().
                when().log().all().
                get(endpoint);
        log.info(response.asString());
    }


    @Step("I should see <200> status code")
    public void checkStatusCode(String code) {
        Assert.assertEquals(response.statusCode(),Integer.parseInt(code));
    }
}
