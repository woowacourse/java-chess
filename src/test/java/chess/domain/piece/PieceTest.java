package chess.domain.piece;

import chess.domain.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @DisplayName("기물이 동일한 색상인지 확인하는 테스트")
    @Test
    void isSameColorTest() {
        // given
        Piece pawn = Pawn.from(Color.WHITE);

        // then
        Assertions.assertThat(pawn.isSameColor(Color.WHITE)).isTrue();
    }

}
