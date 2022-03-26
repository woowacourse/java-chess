package chess.domain.piece;

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
}
