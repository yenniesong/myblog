package com.study.myblog.service;

import com.study.myblog.model.RoleType;
import com.study.myblog.model.User;
import com.study.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service    // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. (loC를 해줌)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword();    // 원본 비밀번호 (실제로 입력한 값)
        String encPassword = encoder.encode(rawPassword);   // 해쉬화 해준 비밀번호
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


}
