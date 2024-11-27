package models.pet.requestModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import framework.enums.PetStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetEditRequest {

	@JsonProperty("id")
	private long id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("name")
	private String name;

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("status")
	private String status;

	@Data
	@Builder
	public static class Category{

		@JsonProperty("id")
		private long id;

		@JsonProperty("name")
		private String name;
	}

	@Data
	@Builder
	public static class TagsItem{

		@JsonProperty("name")
		private String name;

		@JsonProperty("id")
		private long id;
	}
}