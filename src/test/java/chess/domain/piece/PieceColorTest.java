package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceColorTest {

    @DisplayName("반대 색깔을 반환한다.")
    @Test
    void reverseColor() {
        assertThat(PieceColor.WHITE.reverse()).isEqualTo(PieceColor.BLACK);
    }
}
