package chess.domain.piece;

import chess.domain.piece.type.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("입력받은 위치가 기물의 위치와 동일한지 확인한다.")
    @Test
    void isPosition() {
        // given
        final Piece pawn = new Pawn(Color.WHITE, new Position(File.B, Rank.TWO));

        // when
        final boolean isPosition = pawn.isPosition(new Position(File.B, Rank.TWO));

        // then
        Assertions.assertThat(isPosition).isTrue();
    }

    @DisplayName("입력받은 위치가 기물의 위치와 동일하지 않은지 확인한다.")
    @Test
    void isNotPosition() {
        // given
        final Piece pawn = new Pawn(Color.WHITE, new Position(File.B, Rank.TWO));

        // when
        final boolean isPosition = pawn.isPosition(new Position(File.B, Rank.THREE));

        // then
        Assertions.assertThat(isPosition).isFalse();
    }
}
