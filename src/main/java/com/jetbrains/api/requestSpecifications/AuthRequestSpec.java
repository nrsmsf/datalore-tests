package com.jetbrains.api.requestSpecifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthRequestSpec {
    public static RequestSpecification authRequestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new AllureRestAssured());
        requestSpecBuilder.setBaseUri("https://k8s.stable.on-premise.datalore.io/api");
        requestSpecBuilder.setBasePath("user_management/v1/login/authenticate");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }
}