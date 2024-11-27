package framework.controllers;

import framework.DataGenerators;
import models.pet.requestModel.PetCreateRequest;
import models.pet.requestModel.PetCreateRequest.Category;
import models.pet.requestModel.PetCreateRequest.TagsItem;

import java.util.List;

public class PetController {

    DataGenerators faker = new DataGenerators();


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

    public PetCreateRequest buildRandomPetModel(String status) {
        return PetCreateRequest
            .builder()
            .id(faker.fakeRandomIntFromTo(1, 99))
            .category(buildRandomCategory())
            .name(faker.fakeSymbols(10))
            .photoUrls(List.of(faker.fakeSymbols(10)))
            .tags(List.of(buildRandomTags()))
            .status(status)
            .build();
     }

}
