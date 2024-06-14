package com.triptuner.boundary;

import static com.triptuner.utils.TestUtils.boundaryTestFile;
import static com.triptuner.utils.TestUtils.currentTest;
import static com.triptuner.utils.TestUtils.testReport;
import static com.triptuner.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.triptuner.dto.DestinationDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class DestinationBoundaryTest {

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
	public void testNameNotBlank() throws IOException {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setName("");
		destinationDTO.setType("City");
		destinationDTO.setItineraryId(1L);
		Set<ConstraintViolation<DestinationDTO>> violations = validator.validate(destinationDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameSize() throws IOException {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setName("a".repeat(256)); // Exceeds max size
		destinationDTO.setType("City");
		destinationDTO.setItineraryId(1L);
		Set<ConstraintViolation<DestinationDTO>> violations = validator.validate(destinationDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTypeNotBlank() throws IOException {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setName("Tokyo");
		destinationDTO.setType("");
		destinationDTO.setItineraryId(1L);
		Set<ConstraintViolation<DestinationDTO>> violations = validator.validate(destinationDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTypeSize() throws IOException {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setName("Tokyo");
		destinationDTO.setType("a".repeat(256)); // Exceeds max size
		destinationDTO.setItineraryId(1L);
		Set<ConstraintViolation<DestinationDTO>> violations = validator.validate(destinationDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testItineraryIdNotNull() throws IOException {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setName("Tokyo");
		destinationDTO.setType("City");
		// Not setting itineraryId to test @NotNull
		Set<ConstraintViolation<DestinationDTO>> violations = validator.validate(destinationDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}