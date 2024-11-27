package com.arch.incorp.tests.pets;

import framework.assertions.GetPetAssertions;
import framework.controllers.PetController;
import framework.enums.PetStatus;
import framework.services.ApiService;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.log4j.Log4j2;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.repsonseModel.PetGetResponse;
import models.pet.requestModel.PetCreateRequest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static framework.conditions.Conditions.statusCode;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

@Log4j2
public class GetPetTest extends ApiService {

    private final PetApiService apiService = new PetApiService();

    @DataProvider(name = "pet-statuses")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {PetStatus.AVAILABLE},
                {PetStatus.PENDING},
                {PetStatus.SOLD}
        };
    }

    @Test(dataProvider = "pet-statuses")
    @Description("Get pets by every status")
    public void getPetsByStatus(PetStatus status) {
        // Create pets in all statuses
        PetCreateRequest petModel = new PetController().buildRandomPetModel(status);

        // Send request
        new PetApiService().addPet(petModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);

        // Get pets by status
        List<PetGetResponse> petGetResponse = apiService.getFindByStatus(status)
                .shouldHave(statusCode(200))
                .getBodyAsList(PetGetResponse.class);

        assertFalse(petGetResponse.isEmpty());
    }

    @Test
    @Issue("Should be 400 code with error message 'Invalid status value'")
    @Description("Get pets by wrong status")
    public void getPetsByWrongStatus() {
        // Get pets by wrong status
        List<PetGetResponse> petGetResponse = apiService.getFindByStatus(PetStatus.WRONG_STATUS)
                .shouldHave(statusCode(400))
                .getBodyAsList(PetGetResponse.class);

        assertTrue(petGetResponse.isEmpty());
    }

    @Test
    @Description("Get pet by id")
    public void getPetById() {
        // Create pets in all statuses
        PetCreateRequest petCreateModel = new PetController().buildRandomPetModel(PetStatus.AVAILABLE);

        // Send request
        PetCreateResponse petCreateResponse = new PetApiService().addPet(petCreateModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);

        // Extract pet Id
        long petId = petCreateResponse.getId();

        // get pet by Id
        PetGetResponse petGetResponse = apiService.getPetById(petId)
                .shouldHave(statusCode(200))
                .asPojo(PetGetResponse.class);

        // Assertions
        GetPetAssertions.assertAllFields(petCreateModel, petGetResponse);
    }

}
