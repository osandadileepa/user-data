package com.osanda.example.dto;

import com.osanda.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 4996183692325098316L;

    @NotNull(message = "First Name cannot be empty")
    private String firstName;

    private String lastName;

    private String dateOfBirth;

    @NotNull(message = "Mobile number is mandatory")
    private String mobileNumber;

    private CityDto city;

    public UserDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth() != null ? user.getDateOfBirth().format(DateTimeFormatter.ISO_DATE) : null;
        this.mobileNumber = user.getMobileNumber();
        this.city = user.getCity() != null ? new CityDto(user.getCity()) : null;
    }
}
