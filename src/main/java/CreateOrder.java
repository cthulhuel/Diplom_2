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


}
