package challenge.controllers;

import challenge.dtos.RequestBodyDto;
import challenge.services.EntryAnalysisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntryAnalysisControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryAnalysisService entryAnalysisService;

    @Test
    public void testGetWordFrequency() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();

        String properBody = objectWriter.writeValueAsString(new RequestBodyDto("I love cats", "cat"));
        mockMvc.perform(post("/entry-analysis/frequency")
                .content(properBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(entryAnalysisService, times(1)).getWordFrequency(any());

        String corruptedBody = objectWriter.writeValueAsString(new RequestBodyDto(null, "cat"));
        mockMvc.perform(post("/entry-analysis/frequency")
                .content(corruptedBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
