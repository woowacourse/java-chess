package chess.domain.strategy;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Deque;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopMoveStrategyTest {

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * .b......  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("비숍이 B2 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.RANK2, Column.B);
        MoveStrategy moveStrategy = new BishopMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.NE)).containsExactly(
                        new Position(Row.RANK3, Column.C),
                        new Position(Row.RANK4, Column.D),
                        new Position(Row.RANK5, Column.E),
                        new Position(Row.RANK6, Column.F),
                        new Position(Row.RANK7, Column.G),
                        new Position(Row.RANK8, Column.H)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SE)).containsExactly(
                        new Position(Row.RANK1, Column.C)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SW)).containsExactly(
                        new Position(Row.RANK1, Column.A)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.NW)).containsExactly(
                        new Position(Row.RANK3, Column.A)
                )
        );
    }

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ..b.....  1 (rank 1)
     *
     * abcdefgh
    */
    @Test
    @DisplayName("비숍이 C1 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnCenterPosition() {
        Position position = new Position(Row.RANK1, Column.C);
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = bishopMoveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.NE)).containsExactly(
                        new Position(Row.RANK2, Column.D),
                        new Position(Row.RANK3, Column.E),
                        new Position(Row.RANK4, Column.F),
                        new Position(Row.RANK5, Column.G),
                        new Position(Row.RANK6, Column.H)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SE)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.SW)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.NW)).containsExactly(
                        new Position(Row.RANK2, Column.B),
                        new Position(Row.RANK3, Column.A)
                )
        );
    }
}
