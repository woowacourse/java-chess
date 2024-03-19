import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("체스 말을 만든다.")
    @Test
    void createTest() {
        // given
        Piece piece = new Piece(PieceType.PAWN);

        // when & then
        assertThat(piece.getType())
                .isEqualTo(PieceType.PAWN);
    }
}
