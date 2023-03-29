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
    @DisplayName("Check successful login with correct log-pass")
    public void checkPositiveAuthTest() {
        UserCredentialsRequest user = new UserCredentialsRequest("1nrsmsf@gmail.com",
                DataloreUtils.decodePassword("SCQyYzUlY3dkamI="));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"OK\""));
    }

    @Test
    @DisplayName("Check negative login with wrong log-pass")
    public void checkLoginWrongCredentialsTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(faker.internet().emailAddress(),
                RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check negative login without password")
    public void checkLoginWithoutPassTest() {
        jsonObj.put("email", faker.internet().emailAddress());

        authSteps.auth(jsonObj)
                .then().statusCode(400);
    }

    @Test
    @DisplayName("Check negative login without email")
    public void checkLoginWithoutEmailTest() {
        jsonObj.put("password", RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(jsonObj)
                .then().statusCode(400);
    }

    @Test
    @DisplayName("Check negative login with empty body")
    public void checkLoginWithoutBodyTest() {
        System.out.println(jsonObj);
        authSteps.auth(jsonObj)
                .then().statusCode(400);
    }

    @Test
    @DisplayName("Check negative login with empty email")
    public void checkLoginWithEmptyEmailTest() {
        UserCredentialsRequest user = new UserCredentialsRequest("",
                RandomStringUtils.randomAlphanumeric(5));

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }

    @Test
    @DisplayName("Check negative login with empty password")
    public void checkLoginWithEmptyPassTest() {
        UserCredentialsRequest user = new UserCredentialsRequest(faker.internet().emailAddress(),
                "");

        authSteps.auth(user)
                .then().statusCode(200)
                .body(equalTo("\"INCORRECT\""));
    }
}
