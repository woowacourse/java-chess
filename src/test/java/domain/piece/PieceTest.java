package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("WHITE의 말인지 여부 확인")
    void isWhite() {
        Rook rook = new Rook(Color.WHITE);
        assertThat(rook.isWhite()).isTrue();
    }
}
