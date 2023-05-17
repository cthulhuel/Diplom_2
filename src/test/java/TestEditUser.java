import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class TestEditUser {

    public String accessToken;
    private static final String USER = "/api/auth/user";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Edit data user with auth")
    public void testEditDataUserWithAuth() {

        EditUser editUser = new EditUser();
        Response getDataUserWithAuth = editUser.getDataUser(new User("eliseev_23@gmail.com","qwerty124", "john"));
        accessToken = getDataUserWithAuth.path("accessToken");

        Body body = new Body("eliseev_23@gmail.com", "qwerty124", "johny");

        Response editDataUserWithAuth = given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(body)
                .when()
                .patch(USER);

        editDataUserWithAuth.then().assertThat().body("success", is(true));

    }

    @Test
    @DisplayName("Edit data user without auth")
    public void testEditDataUserWithoutAuth() {
        EditUser editUser = new EditUser();
        Response getDataUserWithoutAuth = editUser.getEditDataUserWithoutAuth(new User("eliseev_23@gmail.com","qwerty124", "john"));
        getDataUserWithoutAuth.then().statusCode(SC_UNAUTHORIZED).and().assertThat().body("success", is(false), "message", is("You should be authorised"));
    }

}
