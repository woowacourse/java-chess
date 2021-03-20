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
        List<List<Position>> moveStrategy = rook.moveStrategy(Position.valueOf("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(
                Position.valueOf("c6"), Position.valueOf("c7"),
                Position.valueOf("c8")
        );
        assertThat(moveStrategy.get(1)).containsExactly(
                Position.valueOf("c4"), Position.valueOf("c3"),
                Position.valueOf("c2"), Position.valueOf("c1")
        );
        assertThat(moveStrategy.get(2)).containsExactly(
                Position.valueOf("b5"), Position.valueOf("a5")
        );
        assertThat(moveStrategy.get(3)).containsExactly(
                Position.valueOf("d5"), Position.valueOf("e5"),
                Position.valueOf("f5"), Position.valueOf("g5"),
                Position.valueOf("h5")
        );
    }
}
