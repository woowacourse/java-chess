package chess.domain.piece;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    @DisplayName("룩 생성 테스트")
    void createTest() {
        assertThat(Rook.createBlack()).isInstanceOf(Rook.class);
        assertThat(Rook.createWhite()).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("룩 이동전략 테스트")
    void moveStrategyTest() {
        Rook rook = Rook.createBlack();
        List<List<Position>> moveStrategy = rook.vectors(Position.of("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(
                Position.of("c6"), Position.of("c7"),
                Position.of("c8")
        );
        assertThat(moveStrategy.get(1)).containsExactly(
                Position.of("c4"), Position.of("c3"),
                Position.of("c2"), Position.of("c1")
        );
        assertThat(moveStrategy.get(2)).containsExactly(
                Position.of("b5"), Position.of("a5")
        );
        assertThat(moveStrategy.get(3)).containsExactly(
                Position.of("d5"), Position.of("e5"),
                Position.of("f5"), Position.of("g5"),
                Position.of("h5")
        );
    }
}
