package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class pieceTest {

    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Piece.of(Color.BLACK, Type.KING);
        assertThat(piece).isEqualTo(Piece.of(Color.BLACK, Type.KING));
    }
}
