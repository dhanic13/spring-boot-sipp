package com.si.sipp.dao.system;

import com.si.sipp.entity.system.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {
    User findByUsername(String username);
}
