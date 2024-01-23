package com.study.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    // 빌더 패턴
@Entity // User 클래스가 mysql에 자동으로 테이블이 생성된다.
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private long id; // 시퀀스, auto_increment
    @Column(nullable = false, length = 30)
    private String username; // 아이디
    @Column(nullable = false, length = 100)// 넉넉하게 주는 이유는 -> 해시로 변경해서 비밀번호를 암호화 할 것이라서
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role;    // enum을 쓰는게 좋음. 도메인 설정을 할 수 있게 해줌
    @CreationTimestamp  // 시간이 자동으로 입력된다.
    private Timestamp createDate;
}
