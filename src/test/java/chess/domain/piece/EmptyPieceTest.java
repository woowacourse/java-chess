package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyPieceTest {

    @Test
    @DisplayName("빈 말은 움직일 수 없습니다.")
    void isMovableThrowException() {
        final Piece emptyPiece = EmptyPiece.getInstance();

        assertThatThrownBy(() ->
                emptyPiece.isMovable(Position.of("d5"), Position.of("d6")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비어있는 곳은 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("빈 말은 0점이다.")
    void getPoint() {
        final Piece emptyPiece = EmptyPiece.getInstance();

        assertThat(emptyPiece.getPoint()).isEqualTo(0);
    }
}
