package com.study.myblog.service;

import com.study.myblog.model.Board;
import com.study.myblog.model.Reply;
import com.study.myblog.model.User;
import com.study.myblog.repository.BoardRepository;
import com.study.myblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service    // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. (loC를 해줌)
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }
    @Transactional
    public void 글삭제하기(int id){
        boardRepository.deleteById(id);
    }
    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시에 (Service가 종료될 때) 트랜젝션이 종료됨. 이때 더티체킹 - 자동 업데이트가 됨. -> db flush
    }
    @Transactional
    public void 댓글쓰기(User user, int boardId, Reply requestReply) {
        Board board = boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
        });
        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);

    }
}
