package chess.testutil;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Test;

public class H2Connection {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER_ID = "sa";
    private static final String USER_PASSWORD = "";

    public static void setUpTable() {
        try {
            String rootPath = System.getProperty("user.dir");
            RunScript.execute(getConnection(), new FileReader(rootPath + "/docker/db/mysql/init/init.sql"));
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("init sql 초기화 에러");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER_ID, USER_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("connection 에러");
    }

    @Test
    void connection() throws SQLException {
        Connection connection = H2Connection.getConnection();
        assertNotNull(connection);
        connection.close();
    }

    @Test
    void test() {
        assertDoesNotThrow(H2Connection::setUpTable);
    }
}
