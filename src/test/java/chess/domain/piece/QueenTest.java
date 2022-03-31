package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.QueenMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸이동 전략을 가져온다.")
    void getMoveStrategy() {
        assertThat(new Queen(Team.BLACK).getMoveStrategy()).isInstanceOf(QueenMoveStrategy.class);

    }

    @Test
    @DisplayName("King이 아니다.")
    void isKing() {
        assertThat(new Queen(Team.BLACK).isKing()).isFalse();
    }
}