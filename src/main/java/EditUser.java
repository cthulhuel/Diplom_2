import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class EditUser {

    private static final String USER = "/api/auth/user";
    private static final String LOGIN = "/api/auth/login";


    @Step("Получить данные пользователя")
    public Response getDataUser(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Получить данные пользователя без авторизации")
    public Response getEditDataUserWithoutAuth(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .patch(USER);
    }

    @Step("Получить токен")
    public Response getResponse (String accessToken) {
        Body body = new Body("eliseev_23@gmail.com", "qwerty124", "johny");
        return given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(body)
                .when()
                .patch(USER);
    }

}
