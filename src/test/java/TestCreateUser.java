import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class TestCreateUser extends BaseTest {

    @Test
    @DisplayName("Create unique user")
    public void testCreateUniqueUser() {
        CreateUser createUser = new CreateUser();
        Response correctCreateUniqueUser = createUser.getCreateUser(new User("eliseev_135@gmail.com","qwerty124", "john"));
        accessToken = correctCreateUniqueUser.path("accessToken");
        correctCreateUniqueUser.then().statusCode(SC_OK);

        DeleteUser deleteUser = new DeleteUser( );
        Response correctDelete = deleteUser.getDeleteUser(accessToken);
        correctDelete.then().statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Create existing user")
    public void testCreateExistingUser() {
        CreateUser createUser = new CreateUser();
        Response errorCreateExistingUser = createUser.getCreateUser(new User("eliseev_23@gmail.com","qwerty124", "john"));
        errorCreateExistingUser.then().statusCode(SC_FORBIDDEN).and().assertThat().body("success", is(false), "message", is("User already exists"));;
    }

    @Test
    @DisplayName("Create user without email")
    public void testCreateUserWithoutEmail() {
        CreateUser createUser = new CreateUser();
        Response errorCreateUserWithoutEmail = createUser.getCreateUser(new User("","qwerty124", "john"));
        errorCreateUserWithoutEmail.then().statusCode(SC_FORBIDDEN).and().assertThat().body("success", is(false), "message", is("Email, password and name are required fields"));;
    }

    @Test
    @DisplayName("Create user without password")
    public void testCreateUserWithoutPassword() {
        CreateUser createUser = new CreateUser();
        Response errorCreateUserWithoutPassword = createUser.getCreateUser(new User("eliseev_23@gmail.com","", "john"));
        errorCreateUserWithoutPassword.then().statusCode(SC_FORBIDDEN).and().assertThat().body("success", is(false), "message", is("Email, password and name are required fields"));;
    }

    @Test
    @DisplayName("Create user without name")
    public void testCreateUserWithoutName() {
        CreateUser createUser = new CreateUser();
        Response errorCreateUserWithoutName = createUser.getCreateUser(new User("eliseev_23@gmail.com","", "john"));
        errorCreateUserWithoutName.then().statusCode(SC_FORBIDDEN).and().assertThat().body("success", is(false), "message", is("Email, password and name are required fields"));;
    }

}
