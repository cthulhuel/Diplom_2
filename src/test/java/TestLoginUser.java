import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class TestLoginUser {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Login with existing user")
    public void testLoginWithExistingUser() {
        LoginUser loginUser = new LoginUser();
        Response correctLoginWithExistingUser = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty124", "john"));
        correctLoginWithExistingUser.then().statusCode(SC_OK).and().assertThat().body("success", is(true));
    }

    @Test
    @DisplayName("Login with wrong login")
    public void testLoginWithWrongLogin() {
        LoginUser loginUser = new LoginUser();
        Response errorLoginWithWrongLogin = loginUser.getLoginUser(new User("e2liseev_23@gmail.com","qwerty124", "john"));
        errorLoginWithWrongLogin.then().statusCode(SC_UNAUTHORIZED).and().assertThat().body("success", is(false), "message", is("email or password are incorrect"));
    }

    @Test
    @DisplayName("Login with wrong password")
    public void testLoginWithWrongPassword() {
        LoginUser loginUser = new LoginUser();
        Response errorLoginWithWrongPassword = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty123", "john"));
        errorLoginWithWrongPassword.then().statusCode(SC_UNAUTHORIZED).and().assertThat().body("success", is(false), "message", is("email or password are incorrect"));
    }

}
