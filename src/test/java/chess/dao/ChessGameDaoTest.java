package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.ChessGameComponentDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    ChessGameDao chessGameDao;
    Connection connection;

    @BeforeEach
    void setUp() {
        try {
            chessGameDao = new ChessGameDao();
            connection = chessGameDao.getConnection();
            connection.setAutoCommit(false); // 트랜잭션 시작
            System.setProperty("TEST_ENV", "true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        System.setProperty("TEST_ENV", "false");
    }

    @DisplayName("데이터베이스 연결이 되었는지 확인한다.")
    @Test
    void getConnection() {
        assertThat(connection).isNotNull();
    }

    @DisplayName("데이터베이스에서 전체 데이터를 조회한다.")
    @Test
    void findAll() {
        List<ChessGameComponentDto> dtos = chessGameDao.findAll();

        assertThat(dtos.size()).isEqualTo(16);
    }
}
