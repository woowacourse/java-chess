package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static chess.domain.piece.Fixture.mockChessBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {
    @DisplayName("포지션을 받아 해당 위치의 Piece를 리턴한다.")
    @Test
    void findByPositionTest() {
        assertThat(mockChessBoard.getPieceByPosition(Position.of("a1"))).isInstanceOf(Piece.class);
    }

    @DisplayName("이동할 때 해당 위치에 말이 없으면 예외")
    @Test
    void throwExceptionWhenSquareHasNotPiece() {
        assertThatThrownBy(() -> mockChessBoard.move(mockChessBoard.createMoveRoute(Position.of("a3"), Position.of("b3"))))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에는 말이 없습니다.");
    }

    @DisplayName("말을 움직인다.")
    @Test
    void movePiece() {
        Piece piece = mockChessBoard.getPieceByPosition(Position.of("b2"));
        mockChessBoard.move(mockChessBoard.createMoveRoute(Position.of("b2"), Position.of("b3")));

        assertThat(mockChessBoard.getPieceByPosition(Position.of("b3"))).isEqualTo(piece);
    }
}
