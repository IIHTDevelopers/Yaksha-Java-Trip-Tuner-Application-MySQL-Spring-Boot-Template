package com.triptuner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptuner.entity.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

	// Search for itineraries by name using a case-insensitive search

	// Filter itineraries within a given start and end date range
}
