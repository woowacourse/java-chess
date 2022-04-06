package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    private final GameDao gameDao = new GameDaoImpl(new TestConnectionSetup());

    @DisplayName("게임 저장 테스트")
    @Test
    void save() {
        gameDao.save(1);
    }
}
