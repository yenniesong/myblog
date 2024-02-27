package com.study.myblog.controller.api;

import com.study.myblog.config.auth.PrincipalDetail;
import com.study.myblog.dto.ResponseDto;
import com.study.myblog.model.RoleType;
import com.study.myblog.model.User;
import com.study.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController // 데이터만 넘겨줄 것이기 때문에 restController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.print("UserApiController : save 호출됨");

        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  // 자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson이 실행)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){ // key=value, x-www-form-urlencoded
        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 db에 값은 변경이 됐음
        // 하지만 세션 값은 변경되지 않은 상태이기 때문에 직접 세션 값을 세션 값을 변경해줄 것임!
        // 세션 등록
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
