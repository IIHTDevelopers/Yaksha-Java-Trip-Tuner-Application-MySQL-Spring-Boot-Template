package com.triptuner.exception;

import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.exceptionTestFile;
import static com.triptuner.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static com.triptuner.utils.TestUtils.testReport;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.triptuner.controller.ItineraryController;
import com.triptuner.dto.ItineraryDTO;
import com.triptuner.service.ItineraryService;
import com.triptuner.utils.MasterData;

@WebMvcTest(ItineraryController.class)
@AutoConfigureMockMvc
public class ItineraryExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItineraryService itineraryService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateItineraryInvalidDataException() throws Exception {
		ItineraryDTO itineraryDTO = MasterData.getItineraryDTO();
		itineraryDTO.setName(""); // Invalid data

		mockMvc.perform(MockMvcRequestBuilders.post("/api/itineraries").content(MasterData.asJsonString(itineraryDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		yakshaAssert(currentTest(), true, exceptionTestFile);
	}

	@Test
	public void testGetItineraryByIdResourceNotFoundException() throws Exception {
		Long nonExistentItineraryId = 100L; // Assuming this ID does not exist

		when(itineraryService.getItineraryById(nonExistentItineraryId))
				.thenThrow(new ResourceNotFoundException("Itinerary not found with id: " + nonExistentItineraryId));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/" + nonExistentItineraryId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		yakshaAssert(currentTest(), true, exceptionTestFile);
	}

	@Test
	public void testDeleteItineraryByIdResourceNotFoundException() throws Exception {
		Long nonExistentItineraryId = 100L; // Assuming this ID does not exist

		when(itineraryService.deleteItinerary(nonExistentItineraryId))
				.thenThrow(new ResourceNotFoundException("Itinerary not found with id: " + nonExistentItineraryId));

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/itineraries/" + nonExistentItineraryId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		yakshaAssert(currentTest(), true, exceptionTestFile);
	}
}
