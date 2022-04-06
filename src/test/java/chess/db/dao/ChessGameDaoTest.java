package chess.db.dao;

import chess.domain.ChessGame;
import chess.db.entity.ChessGameEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {

    @Test
    @DisplayName("연결 확인")
    void connection() {
        ChessGameDao chessGameDao = new ChessGameDao();
        Connection connection = chessGameDao.getConnection();

        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("게임 시작")
    void saveWithRollback() throws SQLException {
        ChessGameDao chessGameDao = new ChessGameDao();
        Connection connectionRollback = chessGameDao.getConnection();

        connectionRollback.setAutoCommit(false);
        int chessGameId = chessGameDao.save(new ChessGame());
        connectionRollback.rollback();
    }

    @Test
    @DisplayName("게임 호출")
    void findWithRollback() throws SQLException {
        ChessGameDao chessGameDao = new ChessGameDao();
        Connection connectionRollback = chessGameDao.getConnection();

        connectionRollback.setAutoCommit(false);
        int chessGameId = chessGameDao.save(new ChessGame());

        ChessGameEntity chessGameEntity = chessGameDao.find(1);
        assertThat(chessGameEntity.getId()).isEqualTo(1);

        connectionRollback.rollback();
    }
}