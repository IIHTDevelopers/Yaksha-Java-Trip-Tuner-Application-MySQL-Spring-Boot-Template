package com.triptuner.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.triptuner.dto.ItineraryDTO;
import com.triptuner.service.ItineraryService;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	@Override
	public ItineraryDTO createItinerary(ItineraryDTO itineraryDTO) {
		// write your logic here
		return null;
	}

	@Override
	public List<ItineraryDTO> getAllItineraries() {
		// write your logic here
		return null;
	}

	@Override
	public ItineraryDTO getItineraryById(Long id) {
		// write your logic here
		return null;
	}

	@Override
	public ItineraryDTO updateItinerary(Long id, ItineraryDTO itineraryDTO) {
		// write your logic here
		return null;
	}

	@Override
	public boolean deleteItinerary(Long id) {
		// write your logic here
		return false;
	}

	@Override
	public List<ItineraryDTO> searchItinerariesByName(String name) {
		// write your logic here
		return null;
	}

	@Override
	public List<ItineraryDTO> filterItinerariesByDateRange(Date startDate, Date endDate) {
		// write your logic here
		return null;
	}
}
