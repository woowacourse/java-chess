package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.KingMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("킹이다.")
    void isKing() {
        assertThat(new King(Color.WHITE).isKing()).isTrue();
    }

    @Test
    @DisplayName("킹 이동전략을 가져온다..")
    void getMoveStrategy() {
        assertThat(new King(Color.WHITE).getMoveStrategy()).isInstanceOf(KingMoveStrategy.class);
    }
}