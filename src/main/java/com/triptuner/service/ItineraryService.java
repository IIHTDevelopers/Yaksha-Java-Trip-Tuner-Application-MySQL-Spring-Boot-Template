package com.triptuner.service;

import java.util.Date;
import java.util.List;

import com.triptuner.dto.ItineraryDTO;

public interface ItineraryService {

	ItineraryDTO createItinerary(ItineraryDTO itineraryDTO);

	List<ItineraryDTO> getAllItineraries();

	ItineraryDTO getItineraryById(Long id);

	ItineraryDTO updateItinerary(Long id, ItineraryDTO itineraryDTO);

	boolean deleteItinerary(Long id);

	List<ItineraryDTO> searchItinerariesByName(String name);

	List<ItineraryDTO> filterItinerariesByDateRange(Date startDate, Date endDate);
}
