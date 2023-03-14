package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("기물이 정상적으로 생성되는지 확인한다.")
    void constructor_whenCall_thenSuccess() {
        assertThatCode(() -> new Piece(Color.WHITE, Type.PAWN))
                .isExactlyInstanceOf(Piece.class)
                .doesNotThrowAnyException();
    }
}
