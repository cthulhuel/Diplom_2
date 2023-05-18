import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.After;
import static org.apache.http.HttpStatus.SC_ACCEPTED;


public class BaseTest {

    public String accessToken;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    /*
    @After
    public void clearData() {

        DeleteUser deleteUser = new DeleteUser( );
        Response correctDelete = deleteUser.getDeleteUser(accessToken);
        correctDelete.then().statusCode(SC_ACCEPTED);

    }
     */

}
