package com.triptuner.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.triptuner.dto.DestinationDTO;
import com.triptuner.dto.ItineraryDTO;

public class MasterData {

	public static ItineraryDTO getItineraryDTO() {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setId(1L);
		itineraryDTO.setName("Summer Trip to Japan");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		itineraryDTO.setStartDate(calendar.getTime());
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
		itineraryDTO.setEndDate(calendar.getTime());
		DestinationDTO destination1 = getDestinationDTO(1L, "Tokyo", "City");
		DestinationDTO destination2 = getDestinationDTO(2L, "Kyoto", "Historical");

		return itineraryDTO;
	}

	public static List<ItineraryDTO> getItineraryDTOList() {
		List<ItineraryDTO> itineraryDTOList = new ArrayList<>();
		ItineraryDTO itineraryDTO = getItineraryDTO(); // Reusing the getItineraryDTO method
		itineraryDTOList.add(itineraryDTO);
		return itineraryDTOList;
	}

	public static DestinationDTO getDestinationDTO(Long id, String name, String type) {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setId(id);
		destinationDTO.setName(name);
		destinationDTO.setType(type);
		return destinationDTO;
	}

	public static List<DestinationDTO> getDestinationDTOList() {
		List<DestinationDTO> destinationDTOList = new ArrayList<>();
		DestinationDTO destinationDTO = getDestinationDTO(1L, "Tokyo", "City");
		destinationDTOList.add(destinationDTO);
		return destinationDTOList;
	}

	public static String asJsonString(Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
