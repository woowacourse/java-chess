package chess.domain.dao;

import chess.domain.dto.GameDto;
import chess.domain.game.board.ChessBoardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameDaoTest {

    private Connection connection;
    private GameDao gameDao;

    @BeforeEach
    void set() throws SQLException {
        connection = Connector.getConnection();
        gameDao = new GameDao(connection);
        connection.setAutoCommit(false);
    }

    @Test
    void connection(){
        assertThat(Connector.getConnection()).isNotNull();
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void save() {
        gameDao.save(ChessBoardFactory.initBoard());
    }

}