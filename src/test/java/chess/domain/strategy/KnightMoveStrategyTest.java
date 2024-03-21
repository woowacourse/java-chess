package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Deque;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightMoveStrategyTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * ...n....  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("나이트가 D2 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.RANK2, Column.D);
        MoveStrategy moveStrategy = new KnightMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = moveStrategy.generateMovablePositions(position);

        assertAll(
                () -> assertThat(directionListMap.get(Direction.NNE)).containsExactly(
                        new Position(Row.RANK4, Column.E)
                ),
                () -> assertThat(directionListMap.get(Direction.ENE)).containsExactly(
                        new Position(Row.RANK3, Column.F)
                ),
                () -> assertThat(directionListMap.get(Direction.ESE)).containsExactly(
                        new Position(Row.RANK1, Column.F)
                ),
                () -> assertThat(directionListMap.get(Direction.SSE)).isEmpty(),
                () -> assertThat(directionListMap.get(Direction.SSW)).isEmpty(),
                () -> assertThat(directionListMap.get(Direction.WSW)).containsExactly(
                        new Position(Row.RANK1, Column.B)
                ),
                () -> assertThat(directionListMap.get(Direction.WNW)).containsExactly(
                        new Position(Row.RANK3, Column.B)
                ),
                () -> assertThat(directionListMap.get(Direction.NNW)).containsExactly(
                        new Position(Row.RANK4, Column.C)
                )
        );
    }
}
