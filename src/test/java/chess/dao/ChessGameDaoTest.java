package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.ChessGameComponentDto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    ChessGameDao chessGameDao;

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

    @DisplayName("데이터베이스 연결이 되었는지 확인한다.")
    @Test
    void getConnection() {
        Connection connection = chessGameDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @DisplayName("데이터베이스에서 전체 데이터를 조회한다.")
    @Test
    void findAll() {
        List<ChessGameComponentDto> dtos = chessGameDao.findAll();

        assertThat(dtos.size()).isEqualTo(16);
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
