package com.cristian.tiusers.mapper;

import com.cristian.tiusers.dto.UserDto;
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
        user.setTelephone(userDto.telephone());
        user.setResidenceCity(userDto.residenceCity());
        user.setState(userDto.state());
        return user;
    }

}
