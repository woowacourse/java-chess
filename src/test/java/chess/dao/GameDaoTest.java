package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.GameDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    private GameDao gameDao;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = MySqlConnector.getConnection();
        connection.setAutoCommit(false);
        gameDao = new GameDao(connection);
    }

    @Test
    @DisplayName("데이터베이스에 게임 정보를 저장한다.")
    void save() throws SQLException {
        gameDao.save(new GameDto("테스트 게임", "Started", "black"));
        GameDto game = gameDao.findById("테스트 게임");

        assertThat(game.getState()).isEqualTo("Started");
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에서 아이디를 이용해 게임 정보를 불러온다.")
    void findById() {
        GameDto game = gameDao.findById("First Game");

        assertThat(game.getState()).isEqualTo("Started");
    }

    @Test
    @DisplayName("데이터베이스에서 게임의 상태, 턴 정보를 업데이트한다.")
    void updateById() throws SQLException {
        GameDto gameDto = new GameDto("First Game", "Started", "black");
        gameDao.updateById(gameDto);

        assertThat(gameDao.findById("First Game").getTurn()).isEqualTo("black");
        connection.rollback();
    }
}
