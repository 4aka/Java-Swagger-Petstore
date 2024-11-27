package com.arch.incorp.tests.pets;

import com.arch.incorp.tests.RestApiBaseTest;
import framework.DataGenerators;
import framework.assertions.CreatePetAssertions;
import framework.controllers.PetController;
import framework.enums.PetStatus;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

import static framework.conditions.Conditions.statusCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreatePetTest extends RestApiBaseTest {

    @DataProvider(name = "pet-statuses")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {PetStatus.AVAILABLE.getStatus()},
                {PetStatus.PENDING.getStatus()},
                {PetStatus.SOLD.getStatus()}
        };
    }

    @Test(dataProvider = "pet-statuses")
    @Description("Create new pet")
    public void createNewPet(String status) {
        // Build pet model
        PetCreateRequest petModel = new PetController().buildRandomPetModel(status);

        // Send request
        PetCreateResponse petCreateResponse = new PetApiService().addPet(petModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);

        // Assertions
        CreatePetAssertions.assertCreatePetResponse(petModel, petCreateResponse);
    }
}
