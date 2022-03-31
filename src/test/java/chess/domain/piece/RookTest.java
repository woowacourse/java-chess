package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.RookMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    private Rook rook = new Rook(Team.WHITE);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(rook.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(rook.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(rook.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Rook 이동전략을 생성한다.")
    void getMoveStrategy() {
        assertThat(rook.getMoveStrategy()).isInstanceOf(RookMoveStrategy.class);
    }
}