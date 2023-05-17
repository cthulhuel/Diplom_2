import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class TestCreateOrder {

    public String accessToken;
    private static final String ORDER = "/api/orders";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Create order without ingredients")
    public void testCreateOrderWithoutIngredients() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithoutIngredients = createOrder.getCreateOrder(" { \"ingredients\": [] } ");
        getCreateOrderWithoutIngredients.then().statusCode(SC_BAD_REQUEST).and().assertThat().body("success", is(false), "message", is("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create order with ingredients")
    public void testCreateOrderWithIngredients() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithIngredients = createOrder.getCreateOrder(" { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ");
        getCreateOrderWithIngredients.then().statusCode(SC_OK).and().assertThat().body("success", is(true), "name", is("Бессмертный флюоресцентный бургер"));
    }

    @Test
    @DisplayName("Create order with invalid hash")
    public void testCreateOrderWithInvalidHash() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithInvalidHash = createOrder.getCreateOrder(" { \"ingredients\": [\"60c0c5a71d1f82001bdaaa6\",\"60c0c5a71d1f82001bdaaa6\"] } ");
        getCreateOrderWithInvalidHash.then().statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Create order with auth")
    public void testCreateOrderWithAuth() {

        String body = " { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ";

        LoginUser loginUser = new LoginUser();
        Response correctLoginWithExistingUser = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty124", "john"));
        accessToken = correctLoginWithExistingUser.path("accessToken");

        Response getCreateOrderWithAuth = given ()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(body)
                .when()
                .post(ORDER);

        getCreateOrderWithAuth.then().statusCode(SC_OK).and().assertThat().body("success", is(true));

    }

    @Test
    @DisplayName("Create order without auth")
    public void testCreateOrderWithoutAuth() {

        String body = " { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ";

        LoginUser loginUser = new LoginUser();
        Response correctLoginWithExistingUser = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty124", "john"));

        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithoutAuth = createOrder.getCreateOrder(" { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ");

        getCreateOrderWithoutAuth.then().statusCode(SC_OK).and().assertThat().body("success", is(true));

    }

}
