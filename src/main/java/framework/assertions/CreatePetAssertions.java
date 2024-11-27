package framework.assertions;

import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreatePetAssertions {

        public static void assertAllFields(PetCreateRequest petModel, PetCreateResponse petResponse) {
            assertThat(petModel.getId(), equalTo(petResponse.getId()));
            assertThat(petModel.getCategory().getId(), equalTo(petResponse.getCategory().getId()));
            assertThat(petModel.getCategory().getName(), equalTo(petResponse.getCategory().getName()));
            assertThat(petModel.getName(), equalTo(petResponse.getName()));
            assertThat(petModel.getPhotoUrls(), equalTo(petResponse.getPhotoUrls()));
            assertThat(petModel.getTags().get(0).getId(), equalTo(petResponse.getTags().get(0).getId()));
            assertThat(petModel.getTags().get(0).getName(), equalTo(petResponse.getTags().get(0).getName()));
            assertThat(petModel.getStatus(), equalTo(petResponse.getStatus()));
        }
}
