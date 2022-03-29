package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.BishopMoveStrategy;
import chess.domain.move.KnightMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    @DisplayName("나이트 이동 전략을 가져온다.")
    void getMoveStrategy() {
        assertThat(new Knight(Color.BLACK).getMoveStrategy()).isInstanceOf(KnightMoveStrategy.class);
    }

    @Test
    @DisplayName("King이 아니다.")
    void isKing() {
        assertThat(new Knight(Color.BLACK).isKing()).isFalse();
    }
}