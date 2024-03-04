package com.study.myblog.repository;

import com.study.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

// security 사용에 있어서 아래 코드는 필요가 없으므로 하단에 일단 위치해둠


// DAO와 비슷하다.
// 자동으로 been 등록이 되므로 @Repository 어노테이션 생략 가능
// JPA Naming 전략(쿼리)
//    User findByUsernameAndPassword(String username, String password);
// 실제로는 없지만 내가 만들어준 함수!
// SELECT * FROM user WHERE username = ? AND password = ?; 와 동일

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);