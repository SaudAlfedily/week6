package com.example.homework27.Repository;

import com.example.homework27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUsersByUsername(String username);

    public User findUsersById(Integer id);

}
