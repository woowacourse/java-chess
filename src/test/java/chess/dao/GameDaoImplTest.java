package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        gameDao.deleteById(gameDao.getId());
    }

    @Test
    @DisplayName("Connection 동작을 검증한다.")
    void connection() {
        //when
        Connection connection = gameDao.getConnection();

        //then
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("DB에 현재 게임 정보를 저장한다.")
    void save() {
        assertThatCode(() -> gameDao.save())
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("DB에 존재하는 game 정보를 id로 찾아 삭제한다.")
    void deleteById() {
        gameDao.save();
        gameDao.deleteById(gameDao.getId());
    }
}
