package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Piece(PieceType.KING, Team.BLACK))
                .doesNotThrowAnyException();
    }
}
