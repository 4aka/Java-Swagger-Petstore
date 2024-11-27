package com.arch.incorp.tests.pets;

import framework.controllers.PetController;
import framework.enums.PetStatus;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.repsonseModel.PetEditResponse;
import models.pet.requestModel.PetCreateRequest;
import models.pet.requestModel.PetEditRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static framework.conditions.Conditions.statusCode;

public class EditPetTest {

    private final PetApiService apiService = new PetApiService();

    @Test()
    @Description("Positive. Edit pet status")
    public void editPetStatus() {
        // Build pet model for create
        PetCreateRequest createPetModel = new PetController().buildRandomPetModel(PetStatus.AVAILABLE);

        // Send request
        PetCreateResponse petCreateResponse = new PetApiService().addPet(createPetModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);

        // Assertions
        Assert.assertEquals(createPetModel.getStatus(), PetStatus.AVAILABLE.getStatus());

        // Extract pet Id
        long petId = petCreateResponse.getId();

        // Build pet model for edit
        PetEditRequest editPetModel = new PetController().buildPetModelForEdit(petId, PetStatus.SOLD);

        // Send request
        PetEditResponse petEditResponse = apiService.EditPetById(editPetModel)
                .shouldHave(statusCode(200))
                .asPojo(PetEditResponse.class);

        Assert.assertEquals(petEditResponse.getStatus(), PetStatus.SOLD.getStatus());
    }
}
