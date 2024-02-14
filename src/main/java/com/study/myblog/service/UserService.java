package com.study.myblog.service;

import com.study.myblog.model.User;
import com.study.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service    // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. (loC를 해줌)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void 회원가입(User user){
        userRepository.save(user);
    }
}
