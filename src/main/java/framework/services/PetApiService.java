package framework.services;

import models.pet.requestModel.PetCreateRequest;
import framework.assertions.AssertableResponse;

public class PetApiService extends ApiService {

    public AssertableResponse addPet(PetCreateRequest petModel) {
        return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .body(petModel)
                .when()
                .post("pet"));
    }

    public AssertableResponse getFindByStatus(String status) {
        return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus"));
    }
}
