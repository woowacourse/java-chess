package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {

    private final ChessTestDao dao = new ChessTestDao();
    private final Connection connection = dao.connection();

    @Test
    @DisplayName("auto increment로 게임을 생성한다")
    void createGameTest() {
        try {
            Long gameId = dao.createGame();
            Long gameIdIncreased = dao.createGame();

            long difference = gameIdIncreased - gameId;

            connection.rollback();
            connection.close();
            assertThat(difference).isEqualTo(1L);
        } catch (SQLException e) {

        }
    }
}
