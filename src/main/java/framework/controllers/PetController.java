package framework.controllers;

import framework.DataGenerators;
import framework.enums.PetStatus;
import framework.services.PetApiService;
import models.pet.repsonseModel.PetGetResponse;
import models.pet.requestModel.PetCreateRequest;
import models.pet.requestModel.PetCreateRequest.Category;
import models.pet.requestModel.PetCreateRequest.TagsItem;
import models.pet.requestModel.PetEditRequest;

import java.util.List;

import static framework.conditions.Conditions.statusCode;

public class PetController {

    DataGenerators faker = new DataGenerators();
    private final PetApiService apiService = new PetApiService();


    public Category buildRandomCategory() {
        return Category.builder()
                .id(faker.fakeRandomIntFromTo(1, 99))
                .name(faker.fakeSymbols(10))
                .build();
    }

    public TagsItem buildRandomTags() {
        return TagsItem.builder()
                .id(faker.fakeRandomIntFromTo(1, 99))
                .name(faker.fakeSymbols(10))
                .build();
    }

    public PetCreateRequest buildRandomPetModel(PetStatus status) {
        return PetCreateRequest
            .builder()
            .id(faker.fakeRandomIntFromTo(1, 99))
            .category(buildRandomCategory())
            .name(faker.fakeSymbols(10))
            .photoUrls(List.of(faker.fakeSymbols(10)))
            .tags(List.of(buildRandomTags()))
            .status(status.getStatus())
            .build();
     }

     public<T> PetEditRequest buildPetModelForEdit(long petId, PetStatus status) {
         PetGetResponse petGetResponse = apiService.getPetById(petId)
                 .shouldHave(statusCode(200))
                 .asPojo(PetGetResponse.class);

         return PetEditRequest.builder()
                 .id(petGetResponse.getId())

                 .category(PetEditRequest.Category.builder()
                         .id(petGetResponse.getCategory().getId())
                         .name(petGetResponse.getCategory().getName())
                         .build())
                 .name(petGetResponse.getName())
                 .photoUrls(petGetResponse.getPhotoUrls())

                 .tags(List.of(PetEditRequest.TagsItem.builder()
                         .id(petGetResponse.getTags().get(0).getId())
                         .name(petGetResponse.getTags().get(0).getName())
                         .build()))
                 .status(status.getStatus())
                 .build();
     }

}
