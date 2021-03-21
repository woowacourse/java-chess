package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍 생성 테스트")
    void createTest() {
        assertThat(Bishop.createBlack()).isInstanceOf(Bishop.class);
        assertThat(Bishop.createWhite()).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("비숍 이동전략 테스트")
    void moveStrategyTest() {
        Bishop bishop = Bishop.createWhite();
        List<List<Position>> moveStrategy = bishop.vectors(Position.of("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(
            Position.of("b6"), Position.of("a7")
        );
        assertThat(moveStrategy.get(1)).containsExactly(
            Position.of("b4"), Position.of("a3")
        );
        assertThat(moveStrategy.get(2)).containsExactly(
            Position.of("d6"), Position.of("e7"), Position.of("f8")
        );
        assertThat(moveStrategy.get(3)).containsExactly(
            Position.of("d4"), Position.of("e3"),
            Position.of("f2"), Position.of("g1")
        );
    }
}