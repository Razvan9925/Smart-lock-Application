package com.example.smartlock.service.impl;


import com.example.smartlock.dto.UserDto;
import com.example.smartlock.entity.User;
import com.example.smartlock.exception.DataNotFoundException;
import com.example.smartlock.exception.InvalidDataException;
import com.example.smartlock.repository.UserRepository;
import com.example.smartlock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void addUsers(UserDto userDto) {
        if (userDto == null) {
            throw new DataNotFoundException("User not found!");
        } else {
            userDto.setIdRole(1L);
            userDto.setIsActive(true);
            String password = userDto.getPassword();
            String encode = passwordEncoder.encode(password);
            userDto.setPassword(encode);

            User user = modelMapper.map(userDto, new TypeToken<User>() {
            }.getType());
            usersRepository.save(user);
        }
    }

    @Override
    public Optional<UserDto> getUsersById(Long id) {
        if (id < 0) {
            throw new InvalidDataException("Your id " + id + " it's not valid");
        }
        Optional<User> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new DataNotFoundException("The category with id " + id + " does not exist!");
        } else {
            User user = optionalUsers.get();
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return Optional.of(userDto);
        }
    }

    @Override
    public List<UserDto> getListOfUsers() {
        List<User> usersList = usersRepository.findAll();
        if (usersList.isEmpty()) {
            throw new DataNotFoundException("Category list it's empty");
        } else {
            List<UserDto> userDtoList;
            userDtoList = modelMapper.map(usersList, new TypeToken<List<UserDto>>() {
            }.getType());

            return userDtoList;
        }

    }

    @Override
    public List<UserDto> getUsersByFirstName(String firstName) {
        List<User> userList = usersRepository.findAllByFirstNameIsContainingIgnoreCase(firstName);
        if(userList.isEmpty()) {
            throw new DataNotFoundException("User not found");
        }
        else{
            List<UserDto> userDtoList;
            userDtoList = modelMapper.map(userList, new TypeToken<List<UserDto>>(){
            }.getType());

        return userDtoList;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> categoryOptional = usersRepository.findById(id);
        if (id < 0) {
            throw new InvalidDataException("Your id " + id + " it's not valid, pleas try again with id >=0");
        }
        if (categoryOptional.isEmpty()) {
            throw new DataNotFoundException("User with id " + id + " it's not present in database");
        }
        usersRepository.deleteById(id);
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> optionalUsers = usersRepository.findUsersByEmail(email);
        if (optionalUsers.isEmpty()) {
            throw new DataNotFoundException("The user does not exist!");
        } else {
            User user = optionalUsers.get();

            return Optional.of(user);
        }
    }

    @Override
    public void updateUser(UserDto userDto, Long id) {
        User user = usersRepository.findById(id).orElseThrow(()-> new DataNotFoundException("User with id " + id + " it's not present in database"));
        if(userDto.getPassword()!=""){
            String password = userDto.getPassword();
            String encode = passwordEncoder.encode(password);
            userDto.setPassword(encode);
            user.setPassword(userDto.getPassword());
        }
        user.setAddress(userDto.getAddress());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setIsActive(userDto.getIsActive());
        usersRepository.save(user);
    }

    @Override
    public void updateIsActiveUser(UserDto userDto, Long id) {
        User user = usersRepository.findById(id).orElseThrow(()-> new DataNotFoundException("User with id " + id + " it's not present in database"));
        user.setIsActive(userDto.getIsActive());
        usersRepository.save(user);
    }

    @Override
    public Optional<User> getByResetPasswordToken(String token) {
        Optional<User> optionalUser = usersRepository.findUserByResetPasswordToken(token);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("The user does not exist!");
        } else {
            User user = optionalUser.get();

            return Optional.of(user);
        }


    }

    @Override
    public void  updateResetPasswordToken(String token, String email) {
        Optional<User> optionalUsers = usersRepository.findUsersByEmail(email);
        if (optionalUsers.isEmpty()) {
            throw new DataNotFoundException("The user does not exist!");
        } else {
            User user = optionalUsers.get();
            user.setResetPasswordToken(token);
            usersRepository.save(user);


        }
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        usersRepository.save(user);


    }


}

