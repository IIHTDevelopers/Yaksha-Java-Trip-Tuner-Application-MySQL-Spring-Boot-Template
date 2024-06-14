package com.triptuner.boundary;

import static com.triptuner.utils.TestUtils.boundaryTestFile;
import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.testReport;
import static com.triptuner.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.triptuner.dto.ItineraryDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ItineraryBoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void itineraryNameNotBlank() throws IOException {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setName("");
		itineraryDTO.setStartDate(new Date());
		itineraryDTO.setEndDate(new Date());
		Set<ConstraintViolation<ItineraryDTO>> violations = validator.validate(itineraryDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void itineraryNameSize() throws IOException {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setName("a".repeat(256)); // Exceeds max size
		itineraryDTO.setStartDate(new Date());
		itineraryDTO.setEndDate(new Date());
		Set<ConstraintViolation<ItineraryDTO>> violations = validator.validate(itineraryDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testStartDateNotNull() throws IOException {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setName("Sample Itinerary");
		// Not setting startDate to test @NotNull
		itineraryDTO.setEndDate(new Date());
		Set<ConstraintViolation<ItineraryDTO>> violations = validator.validate(itineraryDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testEndDateNotNull() throws IOException {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setName("Sample Itinerary");
		itineraryDTO.setStartDate(new Date());
		// Not setting endDate to test @NotNull
		Set<ConstraintViolation<ItineraryDTO>> violations = validator.validate(itineraryDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDatesFutureOrPresent() throws IOException {
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setName("Sample Itinerary");
		// Setting dates to the past to test @FutureOrPresent
		itineraryDTO.setStartDate(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)); // 1 day in the past
		itineraryDTO.setEndDate(new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000)); // 2 days in the past
		Set<ConstraintViolation<ItineraryDTO>> violations = validator.validate(itineraryDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
