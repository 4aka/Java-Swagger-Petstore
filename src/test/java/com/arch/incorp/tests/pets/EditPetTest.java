package com.arch.incorp.tests.pets;

import framework.controllers.PetController;
import framework.controllers.PetCreateEntities;
import framework.enums.PetStatus;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import models.pet.repsonseModel.PetEditResponse;
import models.pet.requestModel.PetEditRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static framework.conditions.Conditions.statusCode;

public class EditPetTest {

    private final PetApiService apiService = new PetApiService();

    @Test()
    @Description("Positive. Edit pet status")
    public void editPetStatus() {
        // Create pet and extract pet Id
        long petId = new PetCreateEntities().createAvailablePetWithRandomValues().getId();

        // Build pet model for edit
        PetEditRequest editPetModel = new PetController().buildPetModelForEdit(petId, PetStatus.SOLD);

        // Send request
        PetEditResponse petEditResponse = apiService.editPetById(editPetModel)
                .shouldHave(statusCode(200))
                .asPojo(PetEditResponse.class);

        Assert.assertEquals(petEditResponse.getStatus(), PetStatus.SOLD.getStatus());
    }
}
