package com.example.repository.mysql2;

import com.example.model.SQL.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMySQL2Repository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);

}
