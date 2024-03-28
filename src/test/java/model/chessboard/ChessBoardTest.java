package model.chessboard;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    private final ChessBoard chessBoard = new ChessBoard();

    @Test
    @DisplayName("특정 위치에 존재하는 이동 가능한 Pawn을 이동할 때에는 예외를 던지지 않는다.")
    void move_ShouldMovePawnPieceHolder_WhenLegalSourceDestination() {
        Position whitePawnPosition = Position.of(1, 2);
        Position destination = Position.of(1, 3);

        assertDoesNotThrow(() -> chessBoard.move(whitePawnPosition, destination));
    }

    @Test
    @DisplayName("특정 위치에 존재하는 기물들을 가능한 이동 실행할 때에는 예외를 던지지 않는다.")
    void move_ShouldMoveRookPieceHolder_WhenLegalSourceDestination() {
        assertAll(() -> {
            chessBoard.move(Position.of(6, 2), Position.of(6, 3));
            chessBoard.move(Position.of(5, 7), Position.of(5, 5)); // e7 e5
            chessBoard.move(Position.of(7, 2), Position.of(7, 4)); // g2 g4
            chessBoard.move(Position.of(4, 8), Position.of(8, 4)); // d8 h4 -> CheckMate Black Wins
        });
    }

    @Test
    @DisplayName("경로가 막혀 이동 불가능한 기물을 이동하려 할 때에는 예외와 메세지를 던진다.")
    void move_ShouldThrowsException_WhenMoveUsingIllegalSourceDestination() {
        Position whiteRookPosition = Position.of(1, 1);
        Position illegalDestination = Position.of(1, 3);

        assertAll(() -> {
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> chessBoard.move(whiteRookPosition, illegalDestination));
            assertEquals("경로에 기물이 위치하여 이동할 수 없습니다.", exception.getMessage());
        });
    }

    @Test
    @DisplayName("직전에 움직였던 진영의 말을 움직이면 예외와 메세지를 던진다.")
    void move_ShouldThrowsException_WhenLegalSourceDestination() {
        Position blackPawnPosition = Position.of(1, 7);
        Position destination = Position.of(1, 5);

        assertAll(() -> {
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> chessBoard.move(blackPawnPosition, destination));
            assertEquals("WHITE 진영의 기물을 움직여야 합니다.", exception.getMessage());
        });
    }
}
