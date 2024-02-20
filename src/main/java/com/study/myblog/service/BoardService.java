package com.study.myblog.service;

import com.study.myblog.model.Board;
import com.study.myblog.model.User;
import com.study.myblog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service    // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. (loC를 해줌)
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }


}
