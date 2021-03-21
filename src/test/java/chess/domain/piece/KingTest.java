package chess.domain.piece;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("킹 생성 테스트")
    void createTest() {
        assertThat(King.createBlack()).isInstanceOf(King.class);
        assertThat(King.createWhite()).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("킹 이동전략 테스트")
    void moveStrategyTest() {
        King king = King.createWhite();
        List<List<Position>> moveStrategy = king.vectors(Position.of("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(Position.of("c6"));
        assertThat(moveStrategy.get(1)).containsExactly(Position.of("c4"));
        assertThat(moveStrategy.get(2)).containsExactly(Position.of("b5"));
        assertThat(moveStrategy.get(3)).containsExactly(Position.of("d5"));
        assertThat(moveStrategy.get(4)).containsExactly(Position.of("b6"));
        assertThat(moveStrategy.get(5)).containsExactly(Position.of("b4"));
        assertThat(moveStrategy.get(6)).containsExactly(Position.of("d6"));
        assertThat(moveStrategy.get(7)).containsExactly(Position.of("d4"));
    }
}