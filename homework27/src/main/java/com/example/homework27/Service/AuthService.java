package com.example.homework27.Service;


import com.example.homework27.ApiException.ApiException;
import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
//for test only
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //same as add user
    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }

    //user id not from pathVariable
    public void updateUser(Integer userid, User user) {
        User oldUser = userRepository.findUsersById(userid);
        if (oldUser == null) {

            throw new ApiException("user not registered or you are not th user");


        }
        oldUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(oldUser);


    }

    public void deleteUser(Integer userid) {
        User oldUser = userRepository.findUsersById(userid);
        if (oldUser == null) {

            throw new ApiException("user not registered or you are not th user");


        }
        userRepository.delete(oldUser);

    }


}
