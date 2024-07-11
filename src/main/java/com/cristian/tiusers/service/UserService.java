package com.cristian.tiusers.service;

import com.cristian.tiusers.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getUsersByCompany();

    void saveUser(User user);

    void deleteUserById(long id);

}
