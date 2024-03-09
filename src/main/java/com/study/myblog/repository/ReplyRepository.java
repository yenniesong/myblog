package com.study.myblog.repository;

import com.study.myblog.dto.ReplySaveRequestDto;
import com.study.myblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    // mSave 라는 쿼리는 없음 그러므로 어노테이션을 추가해줘야함
    @Modifying
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, NOW())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌
}
