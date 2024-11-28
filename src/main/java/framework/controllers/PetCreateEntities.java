package framework.controllers;

import framework.enums.PetStatus;
import framework.services.PetApiService;
import io.qameta.allure.Step;
import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;

import static framework.conditions.Conditions.statusCode;

public class PetCreateEntities {

    @Step("Create pet with random values and AVAILABLE status")
    public PetCreateResponse createAvailablePetWithRandomValues() {
        // Build pet model for create
        PetCreateRequest createPetModel = new PetController().buildRandomPetModel(PetStatus.AVAILABLE);

        // Send request
        return new PetApiService().addPet(createPetModel)
                .shouldHave(statusCode(200))
                .asPojo(PetCreateResponse.class);
    }
}
