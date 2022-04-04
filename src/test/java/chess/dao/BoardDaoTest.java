package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.GameState;
import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoardDao 는")
class BoardDaoTest {

    @Test
    @DisplayName("디비에 연결되야 한다.")
    void connection() {
        final BoardDao boardDao = new BoardDao();
        final Connection connection = boardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("보드 초기화를 수행한다.")
    void init_Board() {
        final BoardDao boardDao = new BoardDao();
        assertThatNoException().isThrownBy(boardDao::initBoard);
    }

    @Test
    @DisplayName("게임 상태를 가져올 수 있다.")
    void get_Game_Status() {
        final BoardDao boardDao = new BoardDao();
        final GameState actualGameState = boardDao.getGameStatus(2);
        assertThat(actualGameState).isEqualTo(GameState.READY);
    }

    @Test
    @DisplayName("게임 상태를 바꿀 수 있다.")
    void change_Game_Status() {
        final BoardDao boardDao = new BoardDao();
        assertThatNoException().isThrownBy(
                () -> boardDao.changeGameStatus("READY", 2)
        );
    }

}
