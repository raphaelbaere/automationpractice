package automationpractice.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInDTO {
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
}
