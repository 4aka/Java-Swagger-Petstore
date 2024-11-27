package framework.services;

import framework.assertions.AssertableResponse;
import framework.enums.PetStatus;
import models.pet.requestModel.PetCreateRequest;
import models.pet.requestModel.PetEditRequest;

public class PetApiService extends ApiService {

    public AssertableResponse addPet(PetCreateRequest petModel) {
        return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .body(petModel)
                .when()
                .post("pet"));
    }

    public AssertableResponse getFindByStatus(PetStatus status) {
        return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .queryParam("status", status.getStatus())
                .when()
                .get("/pet/findByStatus"));
    }

    public AssertableResponse getPetById(long petId) {
        return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .queryParam("petId", petId)
                .when()
                .get("/pet/" + petId));
    }

    public AssertableResponse EditPetById(PetEditRequest petRequest) {
         return new AssertableResponse(setUp()
                .spec(REQUEST_SPEC)
                .body(petRequest)
                .when()
                .put("/pet/"));
    }
}
