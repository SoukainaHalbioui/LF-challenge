package challenge.services;

import challenge.dtos.RequestBodyDto;
import challenge.dtos.ResponseBodyDto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EntryAnalysisService {

    public ResponseBodyDto getWordFrequency(RequestBodyDto body) {
        if (!body.getWord().isEmpty() && !body.getEntry().isEmpty()) {
            // extracting the words
            List<String> words = Arrays.asList(body.getEntry().replaceAll("[!?,.:;(){}]", "")
                    .replace("\\[", "").replace("\\]", "").split(" "));
            // calculating the frequency
            Integer frequency = getWordCount(words, body.getWord());
            // prepping the words for similarity calculation
            Set<String> filteredWords = new HashSet<>(words);
            filteredWords.remove(body.getWord());
            // extracting similar words
            List<String> result = new ArrayList<>();
            for (String word: filteredWords) {
                Integer distance = calculateLevenshteinDistance(word, body.getWord());
                if (distance <= 1) {
                    result.add(word);
                }
            }
            // returning the results
            return new ResponseBodyDto(frequency, result);
        }
        return new ResponseBodyDto(0, new ArrayList<>());
    }

    private Integer getWordCount(List<String> words, String searchWord) {
        Integer count = 0;
        for (String word : words) {
            if (word.equals(searchWord)) {
                count++;
            }
        }
        return count;
    }

    private Integer calculateLevenshteinDistance(String a, String b) {
        /* uses the dynamic programming approach to solving Levenshtein distance by filling the distance table
           all the way to right bottom cell that contains the answer */
        var distanceTable = new int[a.length() + 1][b.length() + 1];
        for (var i = 0; i <= a.length(); i++) {
            for (var j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    distanceTable[i][j] = j;
                } else if (j == 0) {
                    distanceTable[i][j] = i;
                } else {
                    Integer substitutionCost = (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1) + distanceTable[i -1][j - 1];
                    Integer deletionCost = distanceTable[i - 1][j] + 1;
                    Integer insertionCost = distanceTable[i][j - 1] + 1;
                    Optional<Integer> minCost = Arrays.asList(substitutionCost, deletionCost, insertionCost).stream().min(Integer::compare);
                    if (minCost.isPresent()) {
                        distanceTable[i][j] = minCost.get();
                    }
                }
            }
        }
        return distanceTable[a.length()][b.length()];
    }
}
