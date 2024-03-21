package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Deque;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenMoveStrategyTest {

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * ...q....  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("퀸이 D2 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.RANK2, Column.d);
        QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy();

        Map<Direction, Deque<Position>> directionListMap = queenMoveStrategy.generateMovablePositions(position);

        assertAll(
                () -> assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.RANK3, Column.d),
                        new Position(Row.RANK4, Column.d),
                        new Position(Row.RANK5, Column.d),
                        new Position(Row.RANK6, Column.d),
                        new Position(Row.RANK7, Column.d),
                        new Position(Row.RANK8, Column.d)
                ),
                () -> assertThat(directionListMap.get(Direction.NE)).containsExactly(
                        new Position(Row.RANK3, Column.e),
                        new Position(Row.RANK4, Column.f),
                        new Position(Row.RANK5, Column.g),
                        new Position(Row.RANK6, Column.h)
                ),
                () -> assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.RANK2, Column.e),
                        new Position(Row.RANK2, Column.f),
                        new Position(Row.RANK2, Column.g),
                        new Position(Row.RANK2, Column.h)
                        ),
                () -> assertThat(directionListMap.get(Direction.SE)).containsExactly(
                        new Position(Row.RANK1, Column.e)
                ),
                () -> assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.RANK1, Column.d)
                ),
                () -> assertThat(directionListMap.get(Direction.SW)).containsExactly(
                        new Position(Row.RANK1, Column.c)
                ),
                () -> assertThat(directionListMap.get(Direction.W)).containsExactly(
                        new Position(Row.RANK2, Column.c),
                        new Position(Row.RANK2, Column.b),
                        new Position(Row.RANK2, Column.a)
                        ),
                () -> assertThat(directionListMap.get(Direction.NW)).containsExactly(
                        new Position(Row.RANK3, Column.c),
                        new Position(Row.RANK4, Column.b),
                        new Position(Row.RANK5, Column.a)
                )
        );
    }
}
