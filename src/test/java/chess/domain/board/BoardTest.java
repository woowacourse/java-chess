package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static chess.domain.piece.Fixture.mockBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @DisplayName("포지션을 받아 해당 위치의 Square를 리턴한다.")
    @Test
    void findByPositionTest() {
        assertThat(mockBoard.findByPosition(Position.of("a1"))).isInstanceOf(Square.class);
    }

    @DisplayName("이동할 때 해당 위치에 말이 없으면 예외")
    @Test
    void throwExceptionWhenSquareHasNotPiece() {
        assertThatThrownBy(() -> mockBoard.move(Position.of("a3"), Position.of("b3")))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치엔 말이 없습니다.");
    }

    @DisplayName("말을 움직인다.")
    @Test
    void movePiece() {
        Piece piece = mockBoard.findByPosition(Position.of("b2")).getPiece();
        mockBoard.move(Position.of("b2"), Position.of("b3"));

        assertThat(mockBoard.findByPosition(Position.of("b3")).getPiece()).isEqualTo(piece);
    }
}
