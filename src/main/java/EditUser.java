import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class EditUser {

    private static final String USER = "/api/auth/user";
    private static final String LOGIN = "/api/auth/login";


    public Response getDataUser(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    public Response getEditDataUserWithoutAuth(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .patch(USER);
    }

}
