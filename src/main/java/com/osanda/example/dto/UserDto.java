package com.osanda.example.dto;

import com.osanda.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 4996183692325098316L;

    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String mobileNumber;

    private CityDto city;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth().format(DateTimeFormatter.ISO_DATE);
        this.mobileNumber = user.getMobileNumber();
        this.city = user.getCity() != null ? new CityDto(user.getCity()) : null;
    }
}
