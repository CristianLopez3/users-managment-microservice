package com.cristian.tiusers.mapper;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.model.User;

public class UserMapper {


    private UserMapper() {
        throw new IllegalArgumentException("Utility class");
    }

    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getName(),
                user.getLastname(),
                user.getAddress(),
                user.getPosition(),
                user.getTelephone(),
                user.getResidenceCity(),
                user.isState(),
                user.getCompany().getId(),
                user.getDepartment().getId()
        );
    }

    public static User dtoToUser(UserDto userDto) {
        if (userDto == null) return null;
        User user = new User();
        user.setName(userDto.name());
        user.setLastname(userDto.lastname());
        user.setAddress(userDto.address());
        user.setPosition(userDto.position());
        user.setTelephone(userDto.telephone());
        user.setResidenceCity(userDto.residenceCity());
        user.setState(userDto.state());
        return user;
    }


    public static UserProjectionDto userToProjectionDto(User user) {
        if (user == null) return null;
        return new UserProjectionDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getAddress(),
                user.isState(),
                user.getDepartment().getName()
        );
    }

}
