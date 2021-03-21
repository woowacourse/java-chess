package chess.domain.piece;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    @DisplayName("퀸 생성 테스트")
    void createTest() {
        assertThat(Queen.createBlack()).isInstanceOf(Queen.class);
        assertThat(Queen.createWhite()).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("퀸 이동전략 테스트")
    void moveStrategyTest() {
        Queen queen = Queen.createWhite();
        List<List<Position>> moveStrategy = queen.vectors(Position.of("c5"));
        assertThat(moveStrategy.get(0)).containsExactly(
                Position.of("c6"), Position.of("c7"), Position.of("c8")
        );
        assertThat(moveStrategy.get(1)).containsExactly(
                Position.of("c4"), Position.of("c3"),
                Position.of("c2"), Position.of("c1")
        );
        assertThat(moveStrategy.get(2)).containsExactly(
                Position.of("b5"), Position.of("a5")
        );
        assertThat(moveStrategy.get(3)).containsExactly(
                Position.of("d5"), Position.of("e5"), Position.of("f5"),
                Position.of("g5"), Position.of("h5")
        );
        assertThat(moveStrategy.get(4)).containsExactly(
                Position.of("b6"), Position.of("a7")
        );
        assertThat(moveStrategy.get(5)).containsExactly(
                Position.of("b4"), Position.of("a3")
        );
        assertThat(moveStrategy.get(6)).containsExactly(
                Position.of("d6"), Position.of("e7"), Position.of("f8")
        );
        assertThat(moveStrategy.get(7)).containsExactly(
                Position.of("d4"), Position.of("e3"),
                Position.of("f2"), Position.of("g1")
        );
    }
}