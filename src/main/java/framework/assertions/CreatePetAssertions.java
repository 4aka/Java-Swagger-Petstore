package framework.assertions;

import models.pet.repsonseModel.PetCreateResponse;
import models.pet.requestModel.PetCreateRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class CreatePetAssertions {

        public static void assertAllFields(PetCreateRequest petModel, PetCreateResponse petResponse) {
            assertEquals(petModel.getId(), petResponse.getId());
            assertEquals(petModel.getCategory().getId(), petResponse.getCategory().getId());
            assertThat(petModel.getCategory().getName(), equalTo(petResponse.getCategory().getName()));
            assertThat(petModel.getName(), equalTo(petResponse.getName()));
            assertThat(petModel.getPhotoUrls(), equalTo(petResponse.getPhotoUrls()));
            assertEquals(petModel.getTags().get(0).getId(), petResponse.getTags().get(0).getId());
            assertThat(petModel.getTags().get(0).getName(), equalTo(petResponse.getTags().get(0).getName()));
            assertThat(petModel.getStatus(), equalTo(petResponse.getStatus()));
        }
}
