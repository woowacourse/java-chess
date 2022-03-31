package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.KingMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    King king = new King(Team.WHITE);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(king.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(king.isKing()).isTrue();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(king.isPawn()).isFalse();
    }

    @Test
    @DisplayName("King 이동전략을 생성한다.")
    void getMoveStrategy() {
        assertThat(king.getMoveStrategy()).isInstanceOf(KingMoveStrategy.class);
    }
}