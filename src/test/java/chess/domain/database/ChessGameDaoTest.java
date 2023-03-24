package chess.domain.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessGame;
import chess.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    void connection() {
        assertThat(chessGameDao.getConnection()).isNotNull();
    }

    @Test
    void save() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatCode(() -> chessGameDao.save("102", chessGame))
                .doesNotThrowAnyException();
    }

    @Test
    void select() {
        ChessGame chessGame = chessGameDao.getGameById("101", 1);
        assertThat(chessGame.getBoard()).hasSize(32);
    }

    @Test
    void get_user_by_id_fail() {
        assertThatThrownBy(() -> chessGameDao.getUserById("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록되지 않은 아이디입니다.");
    }

    @Test
    void get_user_by_id_success() {
        Assertions.assertThatCode(() -> chessGameDao.getUserById("odo27"))
                .doesNotThrowAnyException();
    }

    @Test
    void get_user_by_id_has_id_and_nickname() {
        User user = chessGameDao.getUserById("odo27");
        assertThat(user.getId()).isEqualTo("odo27");
        assertThat(user.getNickname()).isEqualTo("mangmoong");
    }

    @Test
    void get_last_turn_by_id() {
        assertThat(chessGameDao.getLastTurnById("103")).isEqualTo(5);
    }

    @Test
    void get_last_game_id() {
        assertThat(chessGameDao.getLastGameId("odo27")).isEqualTo("102");
    }
}
