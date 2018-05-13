package uk.gov.dwp.ipvalidator.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IPv4ValidatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ValidJsonArrayProduces200() throws Exception {
        mockMvc.perform(post("/validator")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\"1\"]"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void NotValidJsonArrayProduces400() throws Exception {
        mockMvc.perform(post("/validator")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bad\":\"val\"}"))
                .andExpect(status().is4xxClientError());
    }

}
