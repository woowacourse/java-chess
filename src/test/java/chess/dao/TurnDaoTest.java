package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.state.Turn;
import chess.testutil.H2Connection;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    private static TurnDao turnDao;
    private static Connection connection;

    @BeforeAll
    static void beforeAll() throws SQLException {
        H2Connection.setUpTable();
        connection = H2Connection.getConnection();
        connection.setAutoCommit(false);
        turnDao = new TurnDao(connection);
    }

    @AfterEach
    void afterEach() throws SQLException {
        connection.rollback();
    }

    @Test
    @DisplayName("현재 턴 반환")
    void findCurrentTurn() {
        Turn turn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않는다."));
        assertThat(turn).isEqualTo(Turn.END);
    }

    @Test
    @DisplayName("턴 업데이트")
    void updateTurn() {
        // given
        Turn currentTurn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않는다."));
        Turn nextTurn = Turn.WHITE_TURN;

        // when
        int result = turnDao.updateTurn(currentTurn, nextTurn);
        Turn changedTurn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("변경된 현재 턴이 존재하지 않는다."));

        // then
        assertAll(
                () -> assertThat(result).isEqualTo(1),
                () -> assertThat(changedTurn).isEqualTo(Turn.WHITE_TURN)
        );
    }
}
