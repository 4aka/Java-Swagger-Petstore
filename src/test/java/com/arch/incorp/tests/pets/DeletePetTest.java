package com.arch.incorp.tests.pets;

import framework.controllers.PetCreateEntities;
import framework.services.PetApiService;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static framework.conditions.Conditions.statusCode;

public class DeletePetTest {

    private final PetApiService apiService = new PetApiService();

    @Test()
    @Description("Positive. Delete pet by id")
    public void editPetStatus() {
        // Create pet and extract pet Id
        long petId = new PetCreateEntities()
                .createAvailablePetWithRandomValues().getId();

        // Send request
        apiService.deletePetById(petId)
                .shouldHave(statusCode(200));
    }
}
