package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NullPieceTest {
    @DisplayName("빈 기물은 움직일 수 없다.")
    @Test
    void move_a3_a4() {
        final NullPiece nullPiece = new NullPiece(null);

        final Position a3 = Position.from("a3");
        final Position a4 = Position.from("a4");

        assertThatThrownBy(() -> nullPiece.move(a3, a4, (piece) -> {
        }))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("빈 기물을 움직일 수 없습니다.");
    }
}
