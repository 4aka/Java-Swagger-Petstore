package com.arch.incorp.tests.pets;

import framework.assertions.CreatePetAssertions;
import framework.controllers.PetController;
import framework.enums.PetStatus;
import framework.services.ApiService;
import io.qameta.allure.Issue;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static framework.conditions.Conditions.statusCode;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePetTest extends ApiService {

    @DataProvider(name = "pet-statuses")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {PetStatus.AVAILABLE.getStatus()},
                {PetStatus.PENDING.getStatus()},
                {PetStatus.SOLD.getStatus()}
        };
    }

    @Test(dataProvider = "pet-statuses")
    @Description("Positive. Create new pet with all possible statuses")
    public void createNewPet(String status) {
        // Build pet model
        PetCreateRequest petModel = new PetController().buildRandomPetModel(status);

        // Send request
        PetCreateResponse petCreateResponse = new PetApiService().addPet(petModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);

        // Assertions
        CreatePetAssertions.assertAllFields(petModel, petCreateResponse);
    }

    @Test
    @Issue("Should be 405 error. Invalid input")
    @Description("Negative. Create new pet with all possible statuses")
    public void createNewPetWithWrongStatus() {
        // Build pet model
        PetCreateRequest petModel = new PetController().buildRandomPetModel(PetStatus.WRONG_STATUS.getStatus());

        // Send request
        new PetApiService().addPet(petModel).shouldHave(statusCode(400));
    }
}
