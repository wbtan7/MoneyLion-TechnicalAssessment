package com.example.MoneyLionTechAssessment;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import com.example.MoneyLionTechAssessment.models.FeatureEmail;
import com.example.MoneyLionTechAssessment.repositories.FeatureEmailRepository;
import com.example.MoneyLionTechAssessment.services.FeatureEmailService;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MoneyLionTechAssessmentApplicationAPIIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FeatureEmailRepository featureEmailRepository;

	@Autowired
	private FeatureEmailService featureEmailService;

	@Test
	/*
	 * Current assumption: featureEmailService.toggleFeaturebyEmail is working well and unit tested.
	 */
	public void testGetFeature() throws Exception {

		// 1st test: no query args, expect canAcess false
		this.mockMvc.perform(get("/feature"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.canAccess").value(false));

		// 2nd test: query some random values, expect to false
		this.mockMvc.perform(get("/feature").param("featureName", "randomFeature12321"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.canAccess").value(false));

		// 3rd test: toggle enable to false with corresponding featureName/Email, expect false
		String testFeature1 = "testFeature1";
		String testEmail1 = "testEmail1";
		Boolean testToggle1 = false;
		featureEmailService.toggleFeaturebyEmail(testFeature1, testEmail1, testToggle1);
		this.mockMvc.perform(get("/feature").param("featureName", testFeature1).param("email", testEmail1))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.canAccess").value(testToggle1));

		// 4th test: toggle enable to true with corresponding featureName/Email, expect true
		testFeature1 = "testFeature1";
		testEmail1 = "testEmail1";
		testToggle1 = true;
		featureEmailService.toggleFeaturebyEmail(testFeature1, testEmail1, testToggle1);
		this.mockMvc.perform(get("/feature").param("featureName", testFeature1).param("email", testEmail1))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.canAccess").value(testToggle1));

	}

	@Test
	public void testPostFeature() throws Exception {

		// setup data for testing, make sure is false at first, so could change it later
		String jsonString = "{\"featureName\": \"fea1\", \"email\": \"email11\", \"enable\": \"false\"}";
		this.mockMvc.perform(post("/feature").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON));

		// 1st test: change to True, expect Ok
		jsonString = "{\"featureName\": \"fea1\", \"email\": \"email11\", \"enable\": \"true\"}";
		this.mockMvc.perform(post("/feature").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		Optional<FeatureEmail> foundFeatureEmail = featureEmailRepository.findByFeature_FeatureNameAndEmail_Email("fea1", "email11");
		assertThat(foundFeatureEmail.get().isEnable()).isEqualTo(true);

		// 2nd test: submit similar request, expect Not Modified since is same data
		this.mockMvc.perform(post("/feature").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotModified());

		// 3rd test: change to false
		jsonString = "{\"featureName\": \"fea1\", \"email\": \"email11\", \"enable\": \"false\"}";
		this.mockMvc.perform(post("/feature").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		foundFeatureEmail = featureEmailRepository.findByFeature_FeatureNameAndEmail_Email("fea1", "email11");
		assertThat(foundFeatureEmail.get().isEnable()).isEqualTo(false);

		// more edge cases ..

	}

}