package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyPieceTest {

    @Test
    @DisplayName("빈 말은 0점이다.")
    void getPoint() {
        Piece emptyPiece = EmptyPiece.getInstance();
        assertThat(emptyPiece.getPoint()).isZero();
    }
}
