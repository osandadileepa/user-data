package com.osanda.example.service;

import com.osanda.example.dto.UserDto;
import com.osanda.example.entity.City;
import com.osanda.example.entity.User;
import com.osanda.example.exception.UserDetailsNotFoundException;
import com.osanda.example.repository.CityRepository;
import com.osanda.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    public List<UserDto> getAllUsersFromDatabase() {
        return this.userRepository.findAllByIdNotNull().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }


    public UserDto saveUserDetails(UserDto userDto) throws RuntimeException {

        if (userDto == null)
            throw new UserDetailsNotFoundException();

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


}
