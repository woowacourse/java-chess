package web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessGame;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.dto.ChessGameResponse;

class ChessGameDaoTest {

    private ChessGameDao chessGameDao;

    @BeforeEach
    void setUp() {
        chessGameDao = new FakeChessGameDao();
    }

    @Test
    @DisplayName("체스 게임이 정상적으로 저장되는지 확인")
    void createGame() throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        assertThat(chessGameDao.createGame(chessGame)).isEqualTo(1L);
    }

    @Test
    @DisplayName("진행중인 체스 게임 리스트가 정상적인지 확인")
    void findRunningGames() throws SQLException {
        ChessGame chessGame1 = ChessGame.initChessGame();
        ChessGame chessGame2 = ChessGame.initChessGame();
        chessGameDao.createGame(chessGame1);
        chessGameDao.createGame(chessGame2);

        assertThat(chessGameDao.findRunningGames()).hasSize(2);
    }

    @Test
    @DisplayName("gameId로 진행중인 ChessGame을 정상적으로 찾는지 확인")
    void findByGameId() throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        Long gameId = chessGameDao.createGame(chessGame);

        assertThat(chessGameDao.findByGameId(String.valueOf(gameId))).isEqualTo(
            new ChessGameResponse(
                gameId,
                chessGame.getName(),
                chessGame.currentTurn().name()
            )
        );
    }

    @Test
    @DisplayName("진행중인 턴의 색깔을 확인")
    void findTurn() throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        Long gameId = chessGameDao.createGame(chessGame);

        assertThat(chessGameDao.findTurn(String.valueOf(gameId))).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("게임종료로 업데이트하는지 확인")
    void updateGameEnd() throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        Long gameId = chessGameDao.createGame(chessGame);
        chessGameDao.updateGameEnd(gameId);

        assertThat(chessGameDao.findRunningGames()).hasSize(0);
    }
}
