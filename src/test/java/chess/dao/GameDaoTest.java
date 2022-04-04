package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    @DisplayName("기존 data를 덮어쓸 수 있다.")
    @Test
    void save() {
        GameDao gameDao = new GameDao();
        assertThatNoException().isThrownBy(gameDao::save);
    }
}
