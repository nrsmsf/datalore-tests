package com.jetbrains.api.steps;

import static io.restassured.RestAssured.given;

import com.jetbrains.api.requestDTO.UserCredentialsRequest;
import com.jetbrains.api.requestSpecifications.AuthRequestSpec;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

public class AuthSteps {
    @Step("Auth on Datalore landing page")
    @Attachment
    public Response auth(UserCredentialsRequest user) {
        return given()
                .spec(AuthRequestSpec.authRequestSpec())
                .body(user)
                .when()
                    .post();
    }

    @Step("Auth on Datalore landing page with custom JSON")
    @Attachment
    public Response auth(JSONObject json) {
        return given()
                .spec(AuthRequestSpec.authRequestSpec())
                .body(json.toString())
                .when()
                    .post();
    }
    @Step("Send POST request without body")
    @Attachment
    public Response auth() {
        return given()
                .spec(AuthRequestSpec.authRequestSpec())
                .when()
                    .post();
    }


}
