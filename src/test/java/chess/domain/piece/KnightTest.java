package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.KnightMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    private Knight knight = new Knight(Team.WHITE);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(knight.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(knight.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(knight.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Knight 이동전략을 생성한다.")
    void getMoveStrategy() {
        assertThat(knight.getMoveStrategy()).isInstanceOf(KnightMoveStrategy.class);
    }
}