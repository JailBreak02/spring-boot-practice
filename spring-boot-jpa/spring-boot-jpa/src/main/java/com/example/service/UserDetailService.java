package com.example.service;

import com.example.model.UserDetail;
import com.example.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam userDetailParam, Pageable pageable);
}
