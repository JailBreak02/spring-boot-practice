package com.example.mapper;


import com.example.model.User;

import java.util.List;

public interface UserMapper {

    List<com.example.model.User> getAll();

    com.example.model.User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
