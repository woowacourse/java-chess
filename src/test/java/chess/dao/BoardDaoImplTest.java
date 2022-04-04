package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.piece.Team;
import chess.utils.DatabaseUtil;
import java.sql.Connection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoImplTest {

    private static final Connection connection = DatabaseUtil.getConnection();

    @AfterAll
    static void close() {
        DatabaseUtil.closeConnection(connection);
    }

    @DisplayName("board 생성")
    @Test
    void create() {
        BoardDao boardDao = new BoardDaoImpl(connection);
        assertThatNoException().isThrownBy(() -> boardDao.createBoard(Team.WHITE));
    }

    @DisplayName("현재 turn 변경")
    @Test
    void update_turn() {
        BoardDao boardDao = new BoardDaoImpl(connection);
        assertThatNoException().isThrownBy(() -> boardDao.updateTurn(Team.BLACK, 1));
    }
}