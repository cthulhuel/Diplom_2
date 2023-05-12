import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class TestEditUser {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Before
    public void getDataUser(){

        LoginUser loginUser = new LoginUser();
        Response response = loginUser.getLoginUser(new User("eliseev_23@gmail.com","qwerty124", "john"));

    }

    @Test
    @DisplayName("Edit user")
    public void testEditUser() {
        EditUser editUser = new EditUser();
        Response correctLoginWithExistingUser = editUser.getEditUser(new User("eliseev_23@gmail.com","qwerty124", "john_new"));
        correctLoginWithExistingUser.then().statusCode(201).and().assertThat().body("success", is(true));
    }

}
