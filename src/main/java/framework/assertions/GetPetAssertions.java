package framework.assertions;

import models.pet.repsonseModel.PetCreateResponse;
import models.pet.repsonseModel.PetGetResponse;
import models.pet.requestModel.PetCreateRequest;
import org.testng.Assert;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class GetPetAssertions {

        public static void assertAllFields(PetCreateRequest petModel, PetGetResponse petGetResponse) {
            assertEquals(petModel.getId(), petGetResponse.getId());
            assertEquals(petModel.getCategory().getId(), petGetResponse.getCategory().getId());
            assertThat(petModel.getCategory().getName(), equalTo(petGetResponse.getCategory().getName()));
            assertThat(petModel.getName(), equalTo(petGetResponse.getName()));
            assertThat(petModel.getPhotoUrls(), equalTo(petGetResponse.getPhotoUrls()));
            assertEquals(petModel.getTags().get(0).getId(), petGetResponse.getTags().get(0).getId());
            assertThat(petModel.getTags().get(0).getName(), equalTo(petGetResponse.getTags().get(0).getName()));
            assertThat(petModel.getStatus(), equalTo(petGetResponse.getStatus()));
        }
}
