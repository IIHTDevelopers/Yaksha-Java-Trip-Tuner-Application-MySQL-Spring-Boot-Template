package com.triptuner.dto;

public class DestinationDTO {

	private Long id;

	private String name;

	private String type;

	private Long itineraryId;

	public DestinationDTO() {
		super();
	}

	public DestinationDTO(Long id, String name, String type, Long itineraryId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.itineraryId = itineraryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Long itineraryId) {
		this.itineraryId = itineraryId;
	}

	@Override
	public String toString() {
		return "DestinationDTO [id=" + id + ", name=" + name + ", type=" + type + ", itineraryId=" + itineraryId + "]";
	}
}
