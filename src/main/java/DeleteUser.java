import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteUser {

    private static final String DELETE = "/api/auth/user";

    @Step("Удалить пользователя")
    public Response getDeleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE);
    }

}
