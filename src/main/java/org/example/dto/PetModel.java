package org.example.dto;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class PetModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("category")
    private CategoryAndTagsItem category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("tags")
    private List<CategoryAndTagsItem> tags;

    @JsonProperty("status")
    private String status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryAndTagsItem {
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;
    }
}
