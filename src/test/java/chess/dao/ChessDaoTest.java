package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.ChessBoard;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessDaoTest {
    @Test
    @DisplayName("체스 게임의 상태를 DB에 저장한다.")
    void saveGameState() {
        ChessDao chessDao = new ChessDao();
        GameState gameState = new Ready();

        assertThatNoException().isThrownBy(() -> chessDao.saveGameState(gameState));
    }

    @Test
    @DisplayName("체스 보드의 모든 체스피스를 DB에 저장한다.")
    void saveChessBoard() {
        ChessDao chessDao = new ChessDao();

        assertThatNoException().isThrownBy(() -> chessDao.saveChessBoard(ChessBoard.create()));
    }

    @Test
    @DisplayName("체스 보드의 모든 체스 피스를 DB에서 제거한다.")
    void deleteChessBoard() {
        ChessDao chessDao = new ChessDao();

        assertThatNoException().isThrownBy(chessDao::deleteChessBoard);
    }

    @Test
    @DisplayName("체스 게임의 상태를 DB에서 제거한다.")
    void deleteGameState() {
        ChessDao chessDao = new ChessDao();

        assertThatNoException().isThrownBy(chessDao::deleteGameState);
    }
}
