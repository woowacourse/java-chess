package chess.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DaoTest {
    ChessGameDao chessGameDao;

    /*
     * 초기 체스판 상태
     * RNBQKBNR
     * PPPPPPPP
     * ........
     * ........
     * ........
     * ........
     * pppppppp
     * rnbqkbnr
     */
    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao();
        try {
            executeInitScript();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.setProperty("TEST_ENV", "true");
    }

    @AfterEach
    void tearDown() {
        System.setProperty("TEST_ENV", "false");
    }


    private void executeInitScript() throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("docker/db/mysql/init/initfortest.sql"));
             Connection connection = chessGameDao.getConnection();
             Statement statement = connection.createStatement()) {
            String line;
            StringBuilder scriptContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                handleScriptLine(line, scriptContent, statement);
            }
        }
    }

    private void handleScriptLine(String line, StringBuilder scriptContent, Statement statement) throws SQLException {
        if (!line.trim().isEmpty() && !line.trim().startsWith("#")) {
            scriptContent.append(line).append("\n");
            if (line.trim().endsWith(";")) {
                statement.execute(scriptContent.toString());
                scriptContent.setLength(0);
            }
        }
    }
}
