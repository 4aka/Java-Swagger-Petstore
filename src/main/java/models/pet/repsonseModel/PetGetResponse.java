package models.pet.repsonseModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetGetResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("category")
    private PetCreateResponse.Category category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("tags")
    private List<PetCreateResponse.TagsItem> tags;

    @JsonProperty("status")
    private String status;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Category {

        @JsonProperty("id")
        private long id;

        @JsonProperty("name")
        private String name;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TagsItem {

        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        private long id;
    }

}
