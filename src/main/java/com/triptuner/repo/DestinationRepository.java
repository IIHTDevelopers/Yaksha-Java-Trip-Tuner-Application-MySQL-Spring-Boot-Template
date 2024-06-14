package com.triptuner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptuner.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

	// Find destinations by the itinerary ID

	// Find a destination by its ID and itinerary ID

	// Search destinations within an itinerary by name

	// Filter destinations within an itinerary by type
}
