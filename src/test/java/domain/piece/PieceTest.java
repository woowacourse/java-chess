package domain.piece;

import domain.piece.sliding.Bishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceTest {

    @Test
    @DisplayName("칸은 기본 상태를 가진다")
    void propertyTest() {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.getColor() == Color.BLACK).isTrue();
    }
}
