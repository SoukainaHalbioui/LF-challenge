package challenge.services;

import challenge.Application;
import challenge.dtos.RequestBodyDto;
import challenge.dtos.ResponseBodyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EntryAnalysisServiceTest {
    @Autowired
    private EntryAnalysisService entryAnalysisService;

    @Test
    public void testGetWordFrequency() {
        // case 1
        RequestBodyDto input1 = new RequestBodyDto("I would rather beg a bug than be eaten by bugs.", "bug");
        ResponseBodyDto expectedOutput1 = new ResponseBodyDto(1, Arrays.asList("bugs", "beg"));
        ResponseBodyDto actualOutput1 = entryAnalysisService.getWordFrequency(input1);
        assertEquals(expectedOutput1, actualOutput1);

        // case 2
        RequestBodyDto input2 = new RequestBodyDto("", "pickle");
        ResponseBodyDto expectedOutput2 = new ResponseBodyDto(0, new ArrayList<>());
        ResponseBodyDto actualOutput2 = entryAnalysisService.getWordFrequency(input2);
        assertEquals(expectedOutput2, actualOutput2);
    }

}
