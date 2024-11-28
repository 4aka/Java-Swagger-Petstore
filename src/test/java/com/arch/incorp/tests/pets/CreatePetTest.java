package com.arch.incorp.tests.pets;

import framework.assertions.CreatePetAssertions;
import framework.controllers.PetController;
import framework.enums.PetStatus;
import framework.services.ApiService;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static framework.conditions.Conditions.statusCode;

public class CreatePetTest extends ApiService {

    @DataProvider(name = "pet-statuses")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {PetStatus.AVAILABLE},
                {PetStatus.PENDING},
                {PetStatus.SOLD}
        };
    }

    @Test(dataProvider = "pet-statuses")
    @Description("Positive. Create new pet with all possible statuses")
    public void createNewPet(PetStatus status) {
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
        PetCreateRequest petModel = new PetController().buildRandomPetModel(PetStatus.WRONG_STATUS);

        // Send request
        new PetApiService().addPet(petModel).shouldHave(statusCode(400));
    }
}
