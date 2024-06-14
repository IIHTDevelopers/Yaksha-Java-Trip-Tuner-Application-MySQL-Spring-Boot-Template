package com.triptuner.functional;

import static com.triptuner.utils.MasterData.asJsonString;
import static com.triptuner.utils.MasterData.getItineraryDTO;
import static com.triptuner.utils.TestUtils.businessTestFile;
import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.testReport;
import static com.triptuner.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.triptuner.controller.ItineraryController;
import com.triptuner.dto.ItineraryDTO;
import com.triptuner.service.ItineraryService;

@WebMvcTest(ItineraryController.class)
@AutoConfigureMockMvc
public class ItineraryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItineraryService itineraryService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateItinerary() throws Exception {
		ItineraryDTO itineraryDTO = getItineraryDTO();
		when(itineraryService.createItinerary(any(ItineraryDTO.class))).thenReturn(itineraryDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/itineraries")
				.content(asJsonString(itineraryDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.CREATED.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetItineraryById() throws Exception {
		ItineraryDTO itineraryDTO = getItineraryDTO();
		when(itineraryService.getItineraryById(anyLong())).thenReturn(itineraryDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/itineraries/" + itineraryDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(itineraryDTO)) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testDeleteItineraryById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/itineraries/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

		yakshaAssert(currentTest(), true, businessTestFile);
	}

	@Test
	public void testGetAllItineraries() throws Exception {
		List<ItineraryDTO> itineraryDTOList = Arrays.asList(getItineraryDTO());
		when(itineraryService.getAllItineraries()).thenReturn(itineraryDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testSearchItinerariesByName() throws Exception {
		List<ItineraryDTO> itineraryDTOList = Arrays.asList(getItineraryDTO());
		when(itineraryService.searchItinerariesByName(any(String.class))).thenReturn(itineraryDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/search").param("name", "Summer")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	// Example test for filtering itineraries by date range - adjust according to
	// your project's date handling
	@Test
	public void testFilterItinerariesByDateRange() throws Exception {
		List<ItineraryDTO> itineraryDTOList = Arrays.asList(getItineraryDTO());
		when(itineraryService.filterItinerariesByDateRange(any(Date.class), any(Date.class)))
				.thenReturn(itineraryDTOList);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/filter")
				.param("startDate", "2023-01-01").param("endDate", "2023-12-31").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetProfile() throws Exception {
		String expectedProfileData = "This is default profile";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/itineraries/profile").accept(MediaType.TEXT_PLAIN))
				.andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().equals(expectedProfileData) ? "true" : "false",
				businessTestFile);
	}
}
