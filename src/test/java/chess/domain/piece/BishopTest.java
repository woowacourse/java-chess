package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.BishopMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    Bishop bishop = new Bishop(Team.BLACK);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(bishop.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(bishop.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(bishop.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Bishop 이동전략을 생성한다.")
    void getMoveStrategy() {
        assertThat(bishop.getMoveStrategy()).isInstanceOf(BishopMoveStrategy.class);
    }
}