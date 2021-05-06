package challenge.controllers;

import challenge.dtos.RequestBodyDto;
import challenge.dtos.ResponseBodyDto;
import challenge.exceptions.IncompleteBodyException;
import challenge.services.EntryAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping(path = "/entry-analysis")
public class EntryAnalysisController {

    @Autowired
    private EntryAnalysisService entryAnalysisService;

    @PostMapping("/frequency")
    public ResponseEntity<ResponseBodyDto> getWordFrequency(@RequestBody RequestBodyDto body) {
        if (Objects.isNull(body.getEntry()) || Objects.isNull((body.getWord()))) {
            throw new IncompleteBodyException();
        }
        return ResponseEntity.ok(entryAnalysisService.getWordFrequency(body));
    }
}
