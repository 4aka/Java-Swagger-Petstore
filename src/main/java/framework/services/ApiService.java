package framework.services;

import framework.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiService {

    public static final RequestSpecification REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setContentType("application/json")
                    .setBaseUri(Config.restApiBaseUrl)
                    .build();

    @BeforeSuite
    public void addFilters() {
        RestAssured.filters(
                new AllureRestAssured(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }

    protected RequestSpecification setUp() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(Config.restApiBaseUrl)
                .filters(getFilters());
    }

    private List<Filter> getFilters() {
        if (Config.logsEnabled)
            return Arrays.asList(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter(),
                    new AllureRestAssured()
            );
        return Collections.emptyList();
    }
}
