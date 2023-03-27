package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("체스 보드를 입력하면 체스 게임 객체가 생성된다.")
    void shouldSucceedToGenerateChessGame() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();

        Assertions.assertDoesNotThrow(() -> new ChessGame(chessBoard));
    }
}
