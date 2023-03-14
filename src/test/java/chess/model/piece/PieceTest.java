package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("기물이 정상적으로 생성되는지 확인한다.")
    void constructor_givenColorAndType_thenSuccess() {
        final Piece piece = assertDoesNotThrow(() -> new Piece(Color.WHITE, Type.PAWN));

        assertThat(piece).isExactlyInstanceOf(Piece.class);
    }
}
