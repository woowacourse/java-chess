package chess.dao;

import chess.controller.web.dto.game.GameRequestDto;
import chess.domain.manager.ChessManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class GameDaoTest {

    private GameDao gameDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao();
    }

    @Test
    void getConnectionTest() {
        Connection connection = ConnectionProvider.getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }

    @Test
    @DisplayName("Game insert 테스트")
    void saveGameTest() {
        GameRequestDto gameRequestDto =
                new GameRequestDto("white유저임다", "black유저임다", "1:1초보만");
        gameId = gameDao.saveGame(gameRequestDto);

        assertThat(gameId).isNotNull();
    }
}