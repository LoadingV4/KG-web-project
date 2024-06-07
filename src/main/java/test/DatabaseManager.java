package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/springbootdb";
    private static final String DB_USER = "springuser";
    private static final String DB_PASSWORD = "password";

    public void saveUserInfoToDB(String email, String name, String birth) { // 네이버 로그인시 정보 DB에 저장
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO userinfo (email, name, birth) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, birth);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveKakaoUserInfoToDB(String profile,String nickname){

    }
}
