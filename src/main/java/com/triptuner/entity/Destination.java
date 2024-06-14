package com.triptuner.entity;

public class Destination {

	private Long id;

	private String name;

	private String type;

	private Itinerary itinerary;

	public Destination() {
	}

	public Destination(Long id, String name, String type, Itinerary itinerary) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.itinerary = itinerary;
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

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	@Override
	public String toString() {
		return "Destination [id=" + id + ", name=" + name + ", type=" + type + ", itinerary=" + itinerary + "]";
	}
}
