package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final String PARAMETER_FORMAT = "?";
    private static final String PARAMETER_GROUP_DELIMITER = ", ";
    private static final String PARAMETER_GROUP_FORMAT = "(%s)";
    private static final String CONNECTION_FAILED_EXCEPTION_MESSAGE = "데이터베이스 접속에 실패하였습니다.";

    private DatabaseUtil() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(CONNECTION_FAILED_EXCEPTION_MESSAGE);
        }
    }

    public static <T> String parameterGroupsOf(List<T> group, int size) {
        StringJoiner joiner = new StringJoiner(PARAMETER_GROUP_DELIMITER);
        for (int i = 0; i < group.size(); i++) {
            joiner.add(parameterGroupOf(size));
        }
        return joiner.toString();
    }

    public static String parameterGroupOf(int size) {
        StringJoiner joiner = new StringJoiner(PARAMETER_GROUP_DELIMITER);
        for (int i = 0; i < size; i++) {
            joiner.add(PARAMETER_FORMAT);
        }
        return String.format(PARAMETER_GROUP_FORMAT, joiner);
    }
}
