package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import chess.dao.entity.ChessGameEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcChessGameDaoTest {

    JdbcChessGameDao jdbcChessGameDao = JdbcChessGameDao.getInstance();

    @Test
    @DisplayName("DB 커넥션을 정상적으로 가져온다.")
    void getConnection() {
        // when, then
        try (final Connection connection = jdbcChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("이전의 체스 게임이 있는지 여부를 반환한다.")
    void isExistPreviousChessGame() {
        // given
        Long gameId = 1L;

        // when
        jdbcChessGameDao.saveNewChessGame();

        // then
        assertThat(jdbcChessGameDao.isExistPreviousChessGame(gameId)).isTrue();
    }

    @Test
    @DisplayName("게임 ID로 해당 게임 정보를 반환한다.")
    void findChessGameByGameId() {
        // given
        Long gameId = 1L;

        // when
        ChessGameEntity chessGameEntity = jdbcChessGameDao.findChessGameByGameId(gameId);

        // then
        assertThat(chessGameEntity.getId()).isEqualTo(gameId);
    }
}
