import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class LoginUser {

    private static final String LOGIN = "/api/auth/login";

    public Response getLoginUser(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

}
