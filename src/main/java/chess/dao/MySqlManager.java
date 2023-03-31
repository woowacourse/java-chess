package chess.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlManager {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection establishConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    public static void initMySQL() {
        try (final Connection connection = establishConnection();
             final Statement statement = connection.createStatement()) {

            final String initSql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/schema.sql")));

            String[] queries = initSql.split(";");

            for (String query : queries) {
                if (query.trim().isEmpty()) {
                    continue;
                }
                statement.execute(query);
            }

        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
        } catch (IOException e) {
            System.err.println("테이블 초기화 오류" + e.getMessage());
        }
    }
}
