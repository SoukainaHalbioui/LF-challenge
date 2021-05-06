package challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestBodyDto {
    private String entry;
    private String word;
}
