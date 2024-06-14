package com.triptuner.functional;

import static com.triptuner.utils.MasterData.asJsonString;
import static com.triptuner.utils.MasterData.getDestinationDTO;
import static com.triptuner.utils.TestUtils.businessTestFile;
import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.testReport;
import static com.triptuner.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.triptuner.controller.DestinationController;
import com.triptuner.dto.DestinationDTO;
import com.triptuner.service.DestinationService;

@WebMvcTest(DestinationController.class)
@AutoConfigureMockMvc
public class DestinationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DestinationService destinationService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllDestinationsForItinerary() throws Exception {
		List<DestinationDTO> destinationDTOList = Arrays.asList(getDestinationDTO(1L, "Tokyo Tower", "Landmark"));
		when(destinationService.getAllDestinationsForItinerary(anyLong())).thenReturn(destinationDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/1/destinations")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetDestinationByIdAndItineraryId() throws Exception {
		DestinationDTO destinationDTO = getDestinationDTO(1L, "Tokyo Tower", "Landmark");
		when(destinationService.getDestinationByIdAndItineraryId(anyLong(), anyLong())).thenReturn(destinationDTO);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/1/destinations/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(destinationDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testDeleteDestinationFromItinerary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/itineraries/1/destinations/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		yakshaAssert(currentTest(), true, businessTestFile);
	}

	@Test
	public void testSearchDestinationsWithinItineraryByName() throws Exception {
		List<DestinationDTO> destinationDTOList = Arrays.asList(getDestinationDTO(1L, "Tokyo Tower", "Landmark"));
		when(destinationService.searchDestinationsWithinItineraryByName(anyLong(), any(String.class)))
				.thenReturn(destinationDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/1/destinations/search")
				.param("name", "Tokyo").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testFilterDestinationsWithinItineraryByType() throws Exception {
		List<DestinationDTO> destinationDTOList = Arrays.asList(getDestinationDTO(1L, "Tokyo Tower", "Landmark"));
		when(destinationService.filterDestinationsWithinItineraryByType(anyLong(), any(String.class)))
				.thenReturn(destinationDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/1/destinations/filter")
				.param("type", "Landmark").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}
}
