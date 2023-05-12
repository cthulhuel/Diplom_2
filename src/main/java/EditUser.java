import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class EditUser {

    private static final String EDIT = "/api/auth/user";
    private static final String TTT = "";

    public Response getEditUser(Object body) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(TTT)
                .and()
                .body(body)
                .when()
                .patch(EDIT);
    }

}
