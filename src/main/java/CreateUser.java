import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateUser {

    private static final String USER = "/api/auth/register";

    @Step("Создать пользователя")
    public Response getCreateUser(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(USER);
    }

}
