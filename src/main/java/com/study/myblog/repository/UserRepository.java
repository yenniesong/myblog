package com.study.myblog.repository;

import com.study.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // DAO와 비슷하다.
    // 자동으로 been 등록이 되므로 @Repository 어노테이션 생략 가능
}
