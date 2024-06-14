package com.triptuner.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.triptuner.dto.DestinationDTO;
import com.triptuner.service.DestinationService;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Override
	public DestinationDTO addDestinationToItinerary(Long itineraryId, DestinationDTO destinationDTO) {
		// write your logic here
		return null;
	}

	@Override
	public List<DestinationDTO> getAllDestinationsForItinerary(Long itineraryId) {
		// write your logic here
		return null;
	}

	@Override
	public DestinationDTO getDestinationByIdAndItineraryId(Long itineraryId, Long destinationId) {
		// write your logic here
		return null;
	}

	@Override
	public DestinationDTO updateDestination(Long itineraryId, Long destinationId, DestinationDTO destinationDTO) {
		// write your logic here
		return null;
	}

	@Override
	public boolean deleteDestinationFromItinerary(Long itineraryId, Long destinationId) {
		// write your logic here
		return false;
	}

	@Override
	public List<DestinationDTO> searchDestinationsWithinItineraryByName(Long itineraryId, String name) {
		// write your logic here
		return null;
	}

	@Override
	public List<DestinationDTO> filterDestinationsWithinItineraryByType(Long itineraryId, String type) {
		// write your logic here
		return null;
	}
}
