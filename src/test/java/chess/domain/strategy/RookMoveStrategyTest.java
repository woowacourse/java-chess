package chess.domain.strategy;

import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.position.Column;
import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import java.util.Map;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookMoveStrategyTest {

    @Test
    @DisplayName("룩이 A1 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.ONE, Column.A);
        MoveStrategy moveStrategy = new RookMoveStrategy();

        Map<Direction, Queue<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.TWO, Column.A),
                        new Position(Row.THREE, Column.A),
                        new Position(Row.FOUR, Column.A),
                        new Position(Row.FIVE, Column.A),
                        new Position(Row.SIX, Column.A),
                        new Position(Row.SEVEN, Column.A),
                        new Position(Row.EIGHT, Column.A)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.ONE, Column.B),
                        new Position(Row.ONE, Column.C),
                        new Position(Row.ONE, Column.D),
                        new Position(Row.ONE, Column.E),
                        new Position(Row.ONE, Column.F),
                        new Position(Row.ONE, Column.G),
                        new Position(Row.ONE, Column.H)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).isEmpty()
        );
    }

    @Test
    @DisplayName("룩이 D4 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnCenterPosition() {
        Position position = new Position(Row.FOUR, Column.D);
        MoveStrategy moveStrategy = new RookMoveStrategy();

        Map<Direction, Queue<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.FIVE, Column.D),
                        new Position(Row.SIX, Column.D),
                        new Position(Row.SEVEN, Column.D),
                        new Position(Row.EIGHT, Column.D)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.FOUR, Column.E),
                        new Position(Row.FOUR, Column.F),
                        new Position(Row.FOUR, Column.G),
                        new Position(Row.FOUR, Column.H)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.THREE, Column.D),
                        new Position(Row.TWO, Column.D),
                        new Position(Row.ONE, Column.D)
                )
                ,
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).containsExactly(
                        new Position(Row.FOUR, Column.C),
                        new Position(Row.FOUR, Column.B),
                        new Position(Row.FOUR, Column.A)
                )
        );
    }
}
