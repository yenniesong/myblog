package com.study.myblog.controller.api;

import com.study.myblog.dto.ResponseDto;
import com.study.myblog.model.RoleType;
import com.study.myblog.model.User;
import com.study.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 데이터만 넘겨줄 것이기 때문에 restController
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.print("UserApiController : save 호출됨");

        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  // 자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson이 실행)
    }


}
