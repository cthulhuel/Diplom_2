import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class TestUserOrder extends BaseTest {

    public String accessToken;
    private static final String ORDER = "/api/orders";

    @Test
    @DisplayName("Get user order without auth")
    public void testCreateOrderWithoutAuth() {
        UserOrder userOrder = new UserOrder();
        Response getCreateOrderWithoutAuth = userOrder.getUserOrder("");
        getCreateOrderWithoutAuth.then().statusCode(SC_UNAUTHORIZED).and().assertThat().body("success", is(false), "message", is("You should be authorised"));
    }

    @Test
    @DisplayName("Get user order with auth")
    public void testCreateOrderWithAuth() {
        LoginUser loginUser = new LoginUser();
        Response correctLoginWithExistingUser = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty124", "john"));
        accessToken = correctLoginWithExistingUser.path("accessToken");
        UserOrder userOrder = new UserOrder();
        Response getCreateOrderWithAuth = userOrder.getResponse(accessToken);
        getCreateOrderWithAuth.then().statusCode(SC_OK).and().assertThat().body("success", is(true));
    }

}
