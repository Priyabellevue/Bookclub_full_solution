/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URL;

import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@Import(SecurityConfig.class)
@AutoConfigureMockMvc
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class WishlistControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getWishlistController() throws Exception{
        @SuppressWarnings("unused")
        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/").toString(), String.class);
        this.mockMvc.perform(get("/wishlist").with(SecurityMockMvcRequestPostProcessors.user("user"))).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Wishlist")));
        }
}