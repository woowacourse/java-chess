package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.MoveHistoryDto;

class JdbcChessDaoTest {

    JdbcChessDao jdbcChessDao = new JdbcChessDao();

    @BeforeEach
    void setUp() {
//        jdbcChessDao.deleteAllGame();
    }

    @Test
    @DisplayName("DB 커넥션 테스트")
    void connection() throws SQLException {
        try(Connection connection = jdbcChessDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    @DisplayName("addGame을 통해 DB에 게임을 저장한다.")
    void addGame() {
        jdbcChessDao.addGame("테스트");

        List<String> allGame = jdbcChessDao.findAllGame();

        assertThat(allGame).contains("테스트");
    }
    
    @Test
    @DisplayName("saveMoveHistory를 통해 move를 저장한다.")
    void saveMoveHistory() {
        jdbcChessDao.addGame("테스트");
        long gameId = jdbcChessDao.findGameIdByGameName("테스트");
        MoveHistoryDto moveHistoryDto = new MoveHistoryDto("a2", "a3", "EMPTY");
        jdbcChessDao.saveMoveHistory(gameId, moveHistoryDto);

        List<MoveHistoryDto> moveHistoryByGameId = jdbcChessDao.findMoveHistoryByGameId(gameId);

        assertThat(moveHistoryByGameId).contains(moveHistoryDto);
    }
}
