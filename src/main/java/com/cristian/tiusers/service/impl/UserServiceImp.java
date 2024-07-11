package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.model.User;
import com.cristian.tiusers.repository.UserRepository;
import com.cristian.tiusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getUsersByCompany() {
        return null;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {

    }
}
