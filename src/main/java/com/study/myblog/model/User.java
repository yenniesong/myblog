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
//@DynamicInsert    // insert할때 null 인 필드 제외
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment
    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디
    @Column(length = 100)// 넉넉하게 주는 이유는 -> 해시로 변경해서 비밀번호를 암호화 할 것이라서
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("user")
    // DB는 RoleType이라는 게 없다
    @Enumerated(EnumType.STRING)    // 해당 타입이 String인 것을 알려준다.
    private RoleType role;    // enum을 쓰는게 좋음. 도메인 설정을 할 수 있게 해줌.
    // 타입을 RoleType으로 해주면 강제적으로 USER, ADMIN만 나오게 한다.

    private String oauth;   // kakao, google

    @CreationTimestamp  // 시간이 자동으로 입력된다.
    private Timestamp createDate;
}
