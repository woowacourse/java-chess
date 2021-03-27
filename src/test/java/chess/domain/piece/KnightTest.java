package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트 생성 테스트")
    void createKnightTest() {
        assertThat(Knight.createBlack()).isInstanceOf(Knight.class);
        assertThat(Knight.createWhite()).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("나이트 이동전략 테스트")
    void moveStrategyTest() {
        Knight knight = Knight.createWhite();
        List<List<Position>> moveStrategy = knight.vectors(Position.of("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(Position.of("b7"));
        assertThat(moveStrategy.get(1)).containsExactly(Position.of("d7"));
        assertThat(moveStrategy.get(2)).containsExactly(Position.of("b3"));
        assertThat(moveStrategy.get(3)).containsExactly(Position.of("d3"));
        assertThat(moveStrategy.get(4)).containsExactly(Position.of("a6"));
        assertThat(moveStrategy.get(5)).containsExactly(Position.of("a4"));
        assertThat(moveStrategy.get(6)).containsExactly(Position.of("e6"));
        assertThat(moveStrategy.get(7)).containsExactly(Position.of("e4"));
    }
}