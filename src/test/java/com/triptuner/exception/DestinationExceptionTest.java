package com.triptuner.exception;

import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.exceptionTestFile;
import static com.triptuner.utils.TestUtils.testReport;
import static com.triptuner.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
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

import com.triptuner.controller.DestinationController;
import com.triptuner.dto.DestinationDTO;
import com.triptuner.service.DestinationService;
import com.triptuner.utils.MasterData;

@WebMvcTest(DestinationController.class)
@AutoConfigureMockMvc
public class DestinationExceptionTest {

	@MockBean
	private DestinationService destinationService;

	@Mock
	private ModelMapper modelMapper;

	@Autowired
	private MockMvc mockMvc;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDestinationInvalidDataException() throws Exception {
		DestinationDTO destinationDTO = MasterData.getDestinationDTO(1L, "", "");
		destinationDTO.setName("");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/itineraries/1/destinations")
				.content(MasterData.asJsonString(destinationDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateDestinationInvalidDataException() throws Exception {
		DestinationDTO destinationDTO = MasterData.getDestinationDTO(1L, "", "");
		destinationDTO.setName("");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/api/itineraries/1/destinations/" + destinationDTO.getId())
				.content(MasterData.asJsonString(destinationDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetDestinationByIdResourceNotFoundException() throws Exception {
		DestinationDTO destinationDTO = MasterData.getDestinationDTO(1L, "", "");
		destinationDTO.setId(100L);
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Destination not found");

		when(this.destinationService.getDestinationByIdAndItineraryId(1L, destinationDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Destination not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/itineraries/1/destinations/" + destinationDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteDestinationByIdResourceNotFoundException() throws Exception {
		DestinationDTO destinationDTO = MasterData.getDestinationDTO(1L, "", "");
		destinationDTO.setId(100L);
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Destination not found");

		when(this.destinationService.deleteDestinationFromItinerary(1L, destinationDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Destination not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/itineraries/1/destinations/" + destinationDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
