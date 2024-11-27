package framework.services;


import models.pet.requestModel.PetCreateRequest;
import framework.assertions.AssertableResponse;
import io.qameta.allure.Step;

public class PetApiService extends ApiService {

    @Step
    public AssertableResponse addPet(PetCreateRequest petModel) {
        return new AssertableResponse(setUp()
                .body(petModel)
                .when()
                .post("pet"));
    }
}
