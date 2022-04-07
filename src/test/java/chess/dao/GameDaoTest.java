package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import chess.domain.Camp;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    @DisplayName("기존 data를 덮어쓸 수 있다.")
    @Test
    void save() {
        GameDao gameDao = new GameDao();

        assertThatNoException().isThrownBy(() -> gameDao.saveTo("chess_test"));
    }

    @DisplayName("흑색 진영의 차례일 때 게임을 저장하고 불러오면 백색 진영의 차례가 아니다.")
    @Test
    void isWhiteTurnIn_false() {
        GameDao gameDao = new GameDao();
        Camp.initializeTurn();
        Camp.switchTurn();

        boolean isWhiteTurn = false;
        try {
            gameDao.saveTo("chess_test");
            isWhiteTurn = gameDao.isWhiteTurnIn("chess_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertThat(isWhiteTurn).isFalse();
    }
}
