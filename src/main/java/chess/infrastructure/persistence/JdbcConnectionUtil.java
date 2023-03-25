package chess.infrastructure.persistence;

import chess.common.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class JdbcConnectionUtil {

    private static final String HOST; // MySQL 서버 주소
    private static final String USERNAME; //  MySQL 서버 아이디
    private static final String PASSWORD; // MySQL 서버 비밀번호

    static {
        final Map<String, String> properties = PropertiesUtil.parseAll("database-config.properties");
        HOST = properties.get("url");
        USERNAME = properties.get("username");
        PASSWORD = properties.get("password");
    }

    public static Connection connection() {
        try {
            return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            throw new RuntimeException("DB 연결 오류", e);
        }
    }
}
