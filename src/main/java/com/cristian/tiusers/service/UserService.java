package com.cristian.tiusers.service;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserProjectionDto> getUsersByCompany( Long companyId, Pageable pageable);

    void saveUser(UserDto userDto);

    void deleteUserById(long id);

}
