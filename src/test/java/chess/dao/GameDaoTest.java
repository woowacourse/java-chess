package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.MySqlConnector;
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
        gameDao.save(new GameDto("Started", "black"));
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에서 아이디를 이용해 게임 정보를 불러온다.")
    void findById() {
        GameDto game = gameDao.findById(7);

        assertThat(game.getState()).isEqualTo("Ended");
    }

    @Test
    @DisplayName("데이터베이스에서 게임의 상태, 턴 정보를 업데이트한다.")
    void updateById() throws SQLException {
        GameDto gameDto = new GameDto(7, "Started", "white");
        gameDao.updateById(gameDto);

        assertThat(gameDao.findById(7).getState()).isEqualTo("Started");
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에서 가장 최근에 저장 된 게임 정보를 불러온다.")
    void findByMaxId() {
        GameDto game = gameDao.findByMaxId();

        assertThat(game.getTurn()).isEqualTo("black");
    }
}
