package database;

import chess.dao.GameDao;
import chess.dao.ConnectionGenerator;
import chess.dao.GameJdbcDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameDaoTest {
    @Test
    @DisplayName("게임 생성 테스트")
    void create() {
        GameDao gameDao = new GameJdbcDao(ConnectionGenerator.getConnection());
        assertDoesNotThrow(gameDao::create);
    }
}
