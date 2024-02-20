package com.study.myblog.controller;

import com.study.myblog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index(){    // 컨트롤러에서 세션을 어떻게 찾을까
        // /WEB-INF/views/index.jsp
        return "index";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
