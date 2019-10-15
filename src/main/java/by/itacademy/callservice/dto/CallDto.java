package by.itacademy.callservice.dto;

import by.itacademy.callservice.validator.Phone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NotNull
public class CallDto {

    @Size(max = 30)
    private String name;

    @NotBlank(message = "surname is mandatory")
    @Size(max = 30)
    private String surname;

    @Null
    private LocalDateTime time;

    @Phone
    private String number;
}
