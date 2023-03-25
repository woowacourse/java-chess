package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UnitStrategyTest {

    private MoveStrategy strategy;

    @BeforeEach
    void setUpStrategy() {
        strategy = UnitStrategy.instance();
    }

    @DisplayName("한 칸만 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"d4", "d2", "c3", "e3", "c4", "e4", "c2", "e2"})
    void canMove_Once(String position) {
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(strategy.canMove(move)).isTrue();
    }

    @DisplayName("두 칸 이상의 움직임을 할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d1", "d5", "b3", "f3", "b5", "f5"})
    void canNotMove_MoreThanTwice(String position) {
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(strategy.canMove(move)).isFalse();
    }
}
