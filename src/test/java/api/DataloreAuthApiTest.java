package api;

import static org.hamcrest.Matchers.equalTo;

import com.github.javafaker.Faker;
import com.jetbrains.api.requestDTO.UserCredentialsRequest;
import com.jetbrains.api.steps.AuthSteps;
import com.jetbrains.utils.DataloreUtils;
import io.qameta.allure.Epic;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API")
@DisplayName("API Tests for authentication")
public class DataloreAuthApiTest {
    Faker faker = new Faker();
    AuthSteps authSteps = new AuthSteps();

    JSONObject jsonObj = new JSONObject();


    @Test
    @DisplayName("Check that successful login with correct log-pass is handled correctly")
    public void checkPositiveAuthTest() {
        UserCredentialsRequest user = new UserCredentialsRequest("1nrsmsf@gmail.com",
                DataloreUtils.decodePassword("SCQyYzUlY3dkamI="));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"OK\""));
    }

    @Test
    @DisplayName("Check that negative login with wrong log-pass is handled correctly")
    public void checkLoginWrongCredentialsTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(faker.internet().emailAddress(),
                RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check that negative login without password is handled correctly")
    public void checkLoginWithoutPassTest() {
        jsonObj.put("email", faker.internet().emailAddress());

        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }

    @Test
    @DisplayName("Check that negative login without email is handled correctly")
    public void checkLoginWithoutEmailTest() {
        jsonObj.put("password", RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }

  @Test
  @DisplayName("Check that negative login with empty json is handled correctly")
  public void checkLoginWithEmptyJsonTest() {
        System.out.println(jsonObj);
        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }

    @Test
    @DisplayName("Check that negative login with empty email is handled correctly")
    public void checkLoginWithEmptyEmailTest() {
        UserCredentialsRequest user = new UserCredentialsRequest("",
                RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check that negative login with empty password is handled correctly")
    public void checkLoginWithEmptyPassTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(faker.internet().emailAddress(),
                "");

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }
    @Test
    @DisplayName("Check that negative login with empty email and password is handled correctly")
    public void checkLoginWithEmptyEmailPassTest() {
        UserCredentialsRequest user = new UserCredentialsRequest("", "");

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check that incorrect parameter email with correct log-pass is handled correctly")
    public void checkIncorrectEmailParamTest() {
    jsonObj
        .put("emaail", "1nrsmsf@gmail.com")
        .put("password", DataloreUtils.decodePassword("SCQyYzUlY3dkamI="));

        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }
    @Test
    @DisplayName("Check that incorrect parameter password with correct log-pass is handled correctly")
    public void checkIncorrectPasswordParamTest() {
    jsonObj
        .put("email", "1nrsmsf@gmail.com")
        .put("passwoord", DataloreUtils.decodePassword("SCQyYzUlY3dkamI="));

        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }
    @Test
    @DisplayName("Check that incorrect parameters email and password with correct log-pass is handled correctly")
    public void checkIncorrectParamsTest() {
    jsonObj
        .put("emaail", "1nrsmsf@gmail.com")
        .put("passwoord", DataloreUtils.decodePassword("SCQyYzUlY3dkamI="));

        authSteps.auth(jsonObj)
                .then().statusCode(400)
                .body(equalTo(""));
    }
    @Test
    @DisplayName("Check that empty POST request body is handled correctly")
    public void checkEmptyReqBodyTest() {
        authSteps.auth()
                .then().statusCode(400)
                .body(equalTo(""));
    }

    @Test
    @DisplayName("Check that long email is handled correctly")
    public void checkLongEmailTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(RandomStringUtils.randomAlphanumeric(5000)
                + "@gmail.com",
                faker.internet().password());
        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }
    @Test
    @DisplayName("Check that long password is handled correctly")
    public void checkLongPasswordTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(faker.internet().emailAddress(),
                RandomStringUtils.randomAlphanumeric(5000));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check that non-existent parameter with correct log-pass is handled correctly")
    public void checkNonExistParamTest() {
        jsonObj
                .put("email", "1nrsmsf@gmail.com")
                .put("password", DataloreUtils.decodePassword("SCQyYzUlY3dkamI="))
                .put("test", RandomStringUtils.randomAlphanumeric(10));

        authSteps.auth(jsonObj)
                .then().statusCode(200)
                .body(equalTo("\"OK\""));
    }
}
