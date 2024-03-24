package chess.domain.piece;

import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("입력받은 기물의 색과 동일한지 확인한다.")
    @Test
    void isPosition() {
        // given
        final Piece pawn = new Pawn(Color.WHITE);

        // when
        final boolean isMySide = pawn.isMySide(new Rook(Color.WHITE));

        // then
        Assertions.assertThat(isMySide).isTrue();
    }

    @DisplayName("입력받은 기물의 색과 동일하지 않은지 확인한다.")
    @Test
    void isNotPosition() {
        // given
        final Piece pawn = new Pawn(Color.WHITE);

        // when
        final boolean isMySide = pawn.isMySide(new Rook(Color.BLACK));

        // then
        Assertions.assertThat(isMySide).isFalse();
    }
}
