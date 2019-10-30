package com.example.service;

import com.example.model.UserDetail;
import com.example.param.UserDetailParam;
import com.example.repository.UserDetailRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    private UserDetailRepository userDetailRepository;

    @Override
    public Page<UserDetail> findByCondition(UserDetailParam userDetailParam, Pageable pageable) {
        return userDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (!StringUtils.isNullOrEmpty(userDetailParam.getIntroduction())) {
                predicates.add(criteriaBuilder.equal(root.get("introduction"), userDetailParam.getIntroduction()));
            }

            if (!StringUtils.isNullOrEmpty(userDetailParam.getRealName())) {
                predicates.add(criteriaBuilder.like(root.get("realName"), "%" + userDetailParam.getRealName() + "%"));
            }

            if (userDetailParam.getMinAge() != null && userDetailParam.getMaxAge() != null) {
                Predicate predicate = criteriaBuilder.between(root.get("age"), userDetailParam.getMinAge(), userDetailParam.getMaxAge());
                predicates.add(predicate);
            }

            if (userDetailParam.getMinAge() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("age"), userDetailParam.getMinAge()));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        }, pageable);
    }
}
