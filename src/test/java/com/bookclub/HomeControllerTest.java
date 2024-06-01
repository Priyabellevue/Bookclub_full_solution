/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Import(SecurityConfig.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getHomeController() throws Exception {
		this.mockMvc.perform(get("/").with(SecurityMockMvcRequestPostProcessors.user("user"))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Welcome to the Book Club App, your 'one stop shop' for all of your book club needs. Below you will find a list of this months top 10 favorite books. Select the book to see it's details.")));
	}
}
