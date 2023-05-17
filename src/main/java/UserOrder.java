import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserOrder {

    private static final String ORDER = "/api/orders";

    public Response getUserOrder(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .get(ORDER);
    }

    public Response getResponse (String accessToken) {

        return given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .get(ORDER);
    }

}
