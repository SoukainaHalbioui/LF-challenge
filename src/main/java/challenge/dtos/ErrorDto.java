package challenge.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorDto {
    private Date date;
    private HttpStatus status;
    private String message;
}
