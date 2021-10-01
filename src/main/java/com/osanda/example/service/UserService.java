package com.osanda.example.service;

import com.osanda.example.dto.UserDto;
import com.osanda.example.entity.City;
import com.osanda.example.entity.User;
import com.osanda.example.exception.UserDeleteException;
import com.osanda.example.exception.UserDetailsNotFoundException;
import com.osanda.example.exception.UserNotFoundException;
import com.osanda.example.repository.CityRepository;
import com.osanda.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * All the user related operations are included here
 *
 * @author Osanda Wedamulla
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    /***
     * Get all the users
     *
     * @return List<UserDto> all the users available in the database
     */
    public List<UserDto> getAllUsersFromDatabase() {
        return this.userRepository.findAllByIdNotNull().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }


    /***
     * Save user with send data to the database
     *
     * @param userDto
     * @return UserDto all user details
     * @throws RuntimeException
     */
    public UserDto saveUserDetails(UserDto userDto) throws RuntimeException {

        if (userDto == null || userDto.getFirstName() == null || userDto.getMobileNumber() == null)
            throw new UserDetailsNotFoundException("User Details are incorrect or incorrect !!!");

        String cityName = userDto.getCity() != null ? userDto.getCity().getName() : null;

        City city = cityName != null ? this.cityRepository.findByNameIgnoreCase(cityName) : null;

        if (city == null) {
            city = this.cityRepository.save(new City(cityName));
            log.info("New City saved : " + city.getName());
        }


        User user = new User(userDto, city);

        User saveUser = this.userRepository.save(user);
        log.info("New User saved : " + saveUser.getFirstName() + " " + saveUser.getLastName());

        return new UserDto(saveUser);
    }

    /**
     * Update all the details or single detail of the user
     *
     * @param userId primary id of the user
     * @param userDto details needed to be update in the database
     * @return UserDto
     * @throws RuntimeException
     */
    public UserDto updateUserDetails(Long userId, UserDto userDto) throws RuntimeException {

        UserDto updatedUser = null;

        Optional<User> userOptional = this.userRepository.findById(userId);

        if (userOptional.isPresent() && userDto != null) {

            User user = userOptional.get();

            if (userDto.getFirstName() != null)
                user.setFirstName(userDto.getFirstName());
            if (userDto.getLastName() != null)
                user.setLastName(userDto.getLastName());
            if (userDto.getMobileNumber() != null)
                user.setMobileNumber(userDto.getMobileNumber());
            if (userDto.getDateOfBirth() != null)
                user.setDateOfBirth(LocalDate.parse(userDto.getDateOfBirth(), DateTimeFormatter.ISO_DATE));

            if (userDto.getCity() != null && userDto.getCity().getName() != null) {
                String cityName = userDto.getCity().getName();
                City city = this.cityRepository.findByNameIgnoreCase(cityName);

                if (city == null) {
                    city = this.cityRepository.save(new City(cityName));
                    log.info("New City saved : " + city.getName());
                }

                user.setCity(city);
            }

            User updateUser = this.userRepository.save(user);
            log.info("All the user details updated for user ID: " + user.getId());

            updatedUser = new UserDto(updateUser);

        } else {
            throw new UserNotFoundException("User not found with ID : " + userId);
        }

        return updatedUser;
    }

    /***
     * Delete user from the database
     *
     * @param userId user id
     * @throws RuntimeException
     */
    public void deleteUserById(Long userId) throws RuntimeException {

        Optional<User> userOptional = this.userRepository.findById(userId);

        if (userOptional.isPresent()) {
            try {
                this.userRepository.deleteById(userId);
            } catch (Exception e) {
                throw new UserDeleteException("Error deleting user with ID: " + userId);
            }
        } else {
            throw new UserNotFoundException("User not found with ID : " + userId);
        }
    }
}
