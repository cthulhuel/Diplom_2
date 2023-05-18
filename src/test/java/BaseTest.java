import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {

    public String accessToken;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

}
