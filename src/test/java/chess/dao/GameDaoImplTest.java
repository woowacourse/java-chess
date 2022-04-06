package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.Game;

public class GameDaoImplTest {

    private static GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDaoImpl(new Game("white", "black"));
    }

    @Test
    @DisplayName("Connection 동작을 검증한다.")
    void connection() {
        //when
        Connection connection = gameDao.getConnection();

        //then
        assertThat(connection).isNotNull();
    }
}
