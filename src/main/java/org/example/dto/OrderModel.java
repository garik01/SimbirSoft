package org.example.dto;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel{
	@JsonProperty("id")
	private String id;

	@JsonProperty("petId")
	private String petId;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("shipDate")
	private String shipDate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("complete")
	private boolean complete;
}
