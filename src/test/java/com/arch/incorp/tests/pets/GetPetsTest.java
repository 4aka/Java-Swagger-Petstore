package com.arch.incorp.tests.pets;

import com.arch.incorp.tests.RestApiBaseTest;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Log4j2
public class GetPetsTest extends RestApiBaseTest {

    @DataProvider(name = "pet-statuses")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {"available"}, {"pending"}, {"sold"}
        };
    }

    @Test(dataProvider = "pet-statuses")
    @Description("Get all pets with status available")
    public void getAllPets(String status) {

        given()
                .spec(REQUEST_SPEC)
                .queryParam("status", status)
                .get("/pet/findByStatus")
                .then()
                .statusCode(200);
    }

}
