package com.study.myblog.controller.api;

import com.study.myblog.dto.ResponseDto;
import com.study.myblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 데이터만 넘겨줄 것이기 때문에 restController
public class UserApiController {
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.print("UserApiController : save 호출됨");
        // 실제로 db에 insert를 하고 아래에서 return을 구현해준다.

        return new ResponseDto<Integer>(HttpStatus.OK, 1);  // 자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson이 실행)
    }
}
