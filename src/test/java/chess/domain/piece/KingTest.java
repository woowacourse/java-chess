package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.KingMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹이다.")
    void isKing() {
        assertThat(new King(Team.WHITE).isKing()).isTrue();
    }

    @Test
    @DisplayName("킹 이동전략을 가져온다..")
    void getMoveStrategy() {
        assertThat(new King(Team.WHITE).getMoveStrategy()).isInstanceOf(KingMoveStrategy.class);
    }
}