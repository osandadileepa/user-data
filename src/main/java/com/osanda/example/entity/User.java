package com.osanda.example.entity;

import com.osanda.example.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = 5985730429007872956L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @Column(length = 15)
    @Pattern(regexp = "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/")
    private String mobileNumber;

    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private City city;

    public User(UserDto userDto, City city) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.dateOfBirth = userDto.getDateOfBirth() != null ? LocalDate.parse(userDto.getDateOfBirth(), DateTimeFormatter.ISO_DATE) : null;
        this.mobileNumber = userDto.getMobileNumber();
        this.city = city;
    }
}
