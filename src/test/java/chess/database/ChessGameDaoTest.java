package chess.database;

import chess.ChessBoard;
import chess.ChessGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    @Test
    @DisplayName("게임의 생성 정보를 올바르게 DB에 저장한다.")
    void shouldSuccessCreateGame() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        Assertions.assertDoesNotThrow(() -> new ChessGame(chessBoard));
    }
}
