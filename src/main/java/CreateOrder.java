import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateOrder {

    private static final String ORDER = "/api/orders";
    private static final String INGREDIENTS = "/api/ingredients";

    @Step("Получить список ингредиентов")
    public Response getIngredients(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .get(INGREDIENTS);
    }

    @Step("Создать заказ")
    public Response getCreateOrder(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(ORDER);
    }

    @Step("Получить токен")
    public Response getResponse (@NotNull String accessToken) {
        return given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(" { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ")
                .when()
                .post(ORDER);
    }

}
