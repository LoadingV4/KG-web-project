package org.team2project.camealone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userinfo") // 유저정보 테이블
public class UserInfo {
    // 샘플 코드 사용법 ---------------------------------------------------------------------------

    // mariaDB에서 springbootdb 데이터베이스 생성
    // port:3306으로 접속하는 이름 : springuser, 비밀번호 : password 인 유저 생성 후
        // CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'password';
        // GRANT ALL PRIVILEGES ON springbootdb.* TO springuser'@'localhost';
        // FLUSH PRIVILEGES;
    // 샘플 코드 작성 안함

    // 샘플 코드 사용법 ---------------------------------------------------------------------------


    // 컬럼 선언부

    @Id
    @Column(name = "id", nullable = false, unique = true, length = 200)
    private String id;

    @Column(name = "phone", nullable = false, unique = true, length = 200)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // 컬럼 선언부

    public UserInfo(){}

    public UserInfo(String id, String email, String name, String phone, String password){
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
