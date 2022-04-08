package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.QueenMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    private Queen queen = new Queen(Team.WHITE);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(queen.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(queen.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(queen.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Queen 이동전략을 생성한다.")
    void getMoveStrategy() {
        assertThat(queen.getMoveStrategy()).isInstanceOf(QueenMoveStrategy.class);
    }
}