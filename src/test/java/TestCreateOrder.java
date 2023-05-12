import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class TestCreateOrder {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Create order without ingredients")
    public void testCreateOrderWithoutIngredients() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithoutIngredients = createOrder.getCreateOrder(" { \"ingredients\": [] } ");
        getCreateOrderWithoutIngredients.then().statusCode(400).and().assertThat().body("success", is(false), "message", is("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create order with ingredients")
    public void testCreateOrderWithIngredients() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithIngredients = createOrder.getCreateOrder(" { \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa6f\"] } ");
        getCreateOrderWithIngredients.then().statusCode(200).and().assertThat().body("success", is(true), "name", is("Бессмертный флюоресцентный бургер"));
    }

    @Test
    @DisplayName("Create order with invalid hash")
    public void testCreateOrderWithInvalidHash() {
        CreateOrder createOrder = new CreateOrder();
        Response getCreateOrderWithInvalidHash = createOrder.getCreateOrder(" { \"ingredients\": [\"60c0c5a71d1f82001bdaaa6\",\"60c0c5a71d1f82001bdaaa6\"] } ");
        getCreateOrderWithInvalidHash.then().statusCode(500);
    }


}
