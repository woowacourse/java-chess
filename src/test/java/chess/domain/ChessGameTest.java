package chess.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("위치에 같은 팀의 체스 피스가 있는지 확인한다")
    void existsChessPiece() {
        ChessGame chessGame = ChessGame.create();
        ChessBoardPosition position = ChessBoardPosition.of("b7");

        assertTrue(chessGame.existsAllyChessPiece(position));
    }

    @Test
    @DisplayName("위치에 같은 팀의 체스 피스가 없는지 확인지다")
    void notExistsChessPiece() {
        ChessGame chessGame = ChessGame.create();
        ChessBoardPosition position = ChessBoardPosition.of("d2");

        assertFalse(chessGame.existsAllyChessPiece(position));
    }
}
