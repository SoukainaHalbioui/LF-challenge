package challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseBodyDto {
    private Integer frequency;
    private List<String> similarWords;
}
