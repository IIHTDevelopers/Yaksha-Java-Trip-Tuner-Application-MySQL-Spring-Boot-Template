package com.triptuner.service;

import com.triptuner.dto.DestinationDTO;

import java.util.List;

public interface DestinationService {

	DestinationDTO addDestinationToItinerary(Long itineraryId, DestinationDTO destinationDTO);

	List<DestinationDTO> getAllDestinationsForItinerary(Long itineraryId);

	DestinationDTO getDestinationByIdAndItineraryId(Long itineraryId, Long destinationId);

	DestinationDTO updateDestination(Long itineraryId, Long destinationId, DestinationDTO destinationDTO);

	boolean deleteDestinationFromItinerary(Long itineraryId, Long destinationId);

	List<DestinationDTO> searchDestinationsWithinItineraryByName(Long itineraryId, String name);

	List<DestinationDTO> filterDestinationsWithinItineraryByType(Long itineraryId, String type);
}
