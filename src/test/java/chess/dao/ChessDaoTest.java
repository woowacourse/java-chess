package chess.dao;

import chess.controller.web.dto.game.GameRequestDto;
import chess.domain.manager.ChessManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDaoTest {

    private ChessDao chessDao;
    private ChessManager chessManager;
    private Long stateId;
    private Long gameId;

    @BeforeEach
    void setUp() {
        chessDao = new ChessDao();
        chessManager = new ChessManager();
    }

    @Test
    void getConnectionTest() {
        Connection connection = chessDao.getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }

    @Test
    @DisplayName("Game insert 테스트")
    void createGameTest() {
        GameRequestDto gameRequestDto =
                new GameRequestDto("white유저임다", "black유저임다", "1:1초보만");
        gameId = chessDao.saveGame(gameRequestDto);

        assertThat(gameId).isNotNull();
    }

    @Test
    @DisplayName("State insert 테스트")
    void createStateTest() {
        stateId = chessDao.saveState(chessManager, 1L);

        assertThat(stateId).isNotNull();
    }

    @Test
    @DisplayName("Score insert 테스트")
    void createScoreTest() {
        Long scoreId = chessDao.saveScore(chessManager.gameStatus(), 1L);

        assertThat(scoreId).isEqualTo(1);
    }
}