package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.BishopMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("비숍 이동 전략을 가져온다.")
    void getMoveStrategy() {
        assertThat(new Bishop(Team.BLACK).getMoveStrategy()).isInstanceOf(BishopMoveStrategy.class);
    }

    @Test
    @DisplayName("King이 아니다.")
    void isKing() {
        assertThat(new Bishop(Team.BLACK).isKing()).isFalse();
    }
}