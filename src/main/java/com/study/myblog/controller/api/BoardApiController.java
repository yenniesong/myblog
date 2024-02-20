package com.study.myblog.controller.api;

import com.study.myblog.config.auth.PrincipalDetail;
import com.study.myblog.dto.ResponseDto;
import com.study.myblog.model.Board;
import com.study.myblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 데이터만 넘겨줄 것이기 때문에 restController
public class BoardApiController {
    @Autowired
    private BoardService boardService;
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}