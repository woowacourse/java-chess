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
        Position position = new Position(Row.TWO, Column.B);
        MoveStrategy moveStrategy = new BishopMoveStrategy();

        Map<Direction, Queue<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.NE)).containsExactly(
                        new Position(Row.THREE, Column.C),
                        new Position(Row.FOUR, Column.D),
                        new Position(Row.FIVE, Column.E),
                        new Position(Row.SIX, Column.F),
                        new Position(Row.SEVEN, Column.G),
                        new Position(Row.EIGHT, Column.H)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SE)).containsExactly(
                        new Position(Row.ONE, Column.C)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SW)).containsExactly(
                        new Position(Row.ONE, Column.A)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.NW)).containsExactly(
                        new Position(Row.THREE, Column.A)
                )
        );
    }

    /**
     * ........  8 (rank 8) ........  7 ........  6 ........  5 ........  4 ........  3 ........  2 ..b.....  1 (rank
     * 1)
     * <p>
     * abcdefgh
     */
    @Test
    @DisplayName("비숍이 C1 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnCenterPosition() {
        Position position = new Position(Row.ONE, Column.C);
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        Map<Direction, Queue<Position>> directionListMap = bishopMoveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.NE)).containsExactly(
                        new Position(Row.TWO, Column.D),
                        new Position(Row.THREE, Column.E),
                        new Position(Row.FOUR, Column.F),
                        new Position(Row.FIVE, Column.G),
                        new Position(Row.SIX, Column.H)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.SE)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.SW)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.NW)).containsExactly(
                        new Position(Row.TWO, Column.B),
                        new Position(Row.THREE, Column.A)
                )
        );
    }
}
