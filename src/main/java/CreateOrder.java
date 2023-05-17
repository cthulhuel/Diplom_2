import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateOrder {

    private static final String ORDER = "/api/orders";
    private static final String INGREDIENTS = "/api/ingredients";


    public Response getIngredients(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .get(INGREDIENTS);
    }

    public Response getCreateOrder(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(ORDER);
    }

    public Response getResponse (String accessToken) {
        return given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(" { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ")
                .when()
                .post(ORDER);
    }

}
