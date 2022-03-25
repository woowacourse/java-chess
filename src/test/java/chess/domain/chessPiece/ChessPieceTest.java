package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("기물끼리의 색깔을 비교한다.")
    void isSameColor() {
        // given
        final ChessPiece me = new Pawn(Color.BLACK);
        final ChessPiece target = new Pawn(Color.WHITE);

        // when
        final boolean actual = me.isSameColor(target);

        // then
        assertThat(actual).isFalse();
    }
}
