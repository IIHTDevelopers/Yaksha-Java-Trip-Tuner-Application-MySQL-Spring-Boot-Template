package com.triptuner.dto;

import java.util.Date;
import java.util.Set;

public class ItineraryDTO {
	private Long id;

	private String name;

	private Date startDate;

	private Date endDate;

	private Set<DestinationDTO> destinations;

	public ItineraryDTO() {
		super();
	}

	public ItineraryDTO(Long id, String name, Date startDate, Date endDate, Set<DestinationDTO> destinations) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.destinations = destinations;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<DestinationDTO> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<DestinationDTO> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "ItineraryDTO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", destinations=" + destinations + "]";
	}
}
