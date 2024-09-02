package com.machocoders.grades_app;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradesAppApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void showGradeFormTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");
		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andExpect(view().name("form"))
				.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void successfulFormSubmissionTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit").param("name", "Harry")
				.param("subject", "Magic").param("score", "A+");

		mockMvc.perform(request).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void unsuccessfulFormSubmissionTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit").param("name", "")
				.param("subject", "").param("score", "A+");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andExpect(view().name("form"));
	}

	@Test
	public void showGradesPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");
		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andExpect(view().name("grades"))
				.andExpect(model().attributeExists("grades"));
	}

}
