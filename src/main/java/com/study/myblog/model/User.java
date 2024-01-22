package com.study.myblog.model;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity // User 클래스가 mysql에 자동으로 테이블이 생성된다.
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private long id; // 시퀀스, auto_increment
    @Column(nullable = false, length = 30)
    private String username; // 아이디
    private String password;
    private String email;
    private Timestamp createDate;
}
