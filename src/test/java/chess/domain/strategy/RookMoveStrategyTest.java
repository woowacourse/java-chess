package chess.domain.strategy;

import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Row;
import chess.domain.Position;
import java.util.Deque;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookMoveStrategyTest {

    @Test
    @DisplayName("룩이 A1 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.RANK1, Column.A);
        MoveStrategy moveStrategy = new RookMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.RANK2, Column.A),
                        new Position(Row.RANK3, Column.A),
                        new Position(Row.RANK4, Column.A),
                        new Position(Row.RANK5, Column.A),
                        new Position(Row.RANK6, Column.A),
                        new Position(Row.RANK7, Column.A),
                        new Position(Row.RANK8, Column.A)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.RANK1, Column.B),
                        new Position(Row.RANK1, Column.C),
                        new Position(Row.RANK1, Column.D),
                        new Position(Row.RANK1, Column.E),
                        new Position(Row.RANK1, Column.F),
                        new Position(Row.RANK1, Column.G),
                        new Position(Row.RANK1, Column.H)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).isEmpty()
        );
    }

    @Test
    @DisplayName("룩이 D4 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnCenterPosition() {
        Position position = new Position(Row.RANK4, Column.D);
        MoveStrategy moveStrategy = new RookMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.RANK5, Column.D),
                        new Position(Row.RANK6, Column.D),
                        new Position(Row.RANK7, Column.D),
                        new Position(Row.RANK8, Column.D)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.RANK4, Column.E),
                        new Position(Row.RANK4, Column.F),
                        new Position(Row.RANK4, Column.G),
                        new Position(Row.RANK4, Column.H)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.RANK3, Column.D),
                        new Position(Row.RANK2, Column.D),
                        new Position(Row.RANK1, Column.D)
                )
                ,
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).containsExactly(
                        new Position(Row.RANK4, Column.C),
                        new Position(Row.RANK4, Column.B),
                        new Position(Row.RANK4, Column.A)
                )
        );
    }
}
