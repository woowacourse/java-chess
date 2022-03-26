package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyPieceTest {

    @Test
    @DisplayName("빈 피스는 움직일 수 없습니다.")
    void getMovablePositions() {
        Piece emptyPiece = EmptyPiece.getInstance();
        Assertions.assertThatThrownBy(() -> emptyPiece.getMovablePositions(new Position("d5")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 자리에는 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("빈 말은 0점이다.")
    void getPoint() {
        Piece emptyPiece = EmptyPiece.getInstance();
        assertThat(emptyPiece.getPoint()).isEqualTo(0);
    }
}
