package chess.domain.strategy;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Row;
import chess.domain.Position;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookMoveStrategyTest {

    @Test
    @DisplayName("룩이 A1 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnEdgePosition() {
        Position position = new Position(Row.RANK1, Column.a);
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();

        Map<Direction, List<Position>> directionListMap = rookMoveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.RANK2, Column.a),
                        new Position(Row.RANK3, Column.a),
                        new Position(Row.RANK4, Column.a),
                        new Position(Row.RANK5, Column.a),
                        new Position(Row.RANK6, Column.a),
                        new Position(Row.RANK7, Column.a),
                        new Position(Row.RANK8, Column.a)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).isEmpty(),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.RANK1, Column.b),
                        new Position(Row.RANK1, Column.c),
                        new Position(Row.RANK1, Column.d),
                        new Position(Row.RANK1, Column.e),
                        new Position(Row.RANK1, Column.f),
                        new Position(Row.RANK1, Column.g),
                        new Position(Row.RANK1, Column.h)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).isEmpty()
        );
    }

    @Test
    @DisplayName("룩이 D4 에 있을 때 방향에 따라 움직일 수 있는 후보 포지션을 차례대로 저장한다.")
    void calculateCandidateOnCenterPosition() {
        Position position = new Position(Row.RANK4, Column.d);
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();

        Map<Direction, List<Position>> directionListMap = rookMoveStrategy.generateMovablePositions(position);

        assertAll(
                () -> Assertions.assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.RANK5, Column.d),
                        new Position(Row.RANK6, Column.d),
                        new Position(Row.RANK7, Column.d),
                        new Position(Row.RANK8, Column.d)
                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.E)).containsExactly(
                        new Position(Row.RANK4, Column.e),
                        new Position(Row.RANK4, Column.f),
                        new Position(Row.RANK4, Column.g),
                        new Position(Row.RANK4, Column.h)

                ),
                () -> Assertions.assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.RANK3, Column.d),
                        new Position(Row.RANK2, Column.d),
                        new Position(Row.RANK1, Column.d)
                )
                ,
                () -> Assertions.assertThat(directionListMap.get(Direction.W)).containsExactly(
                        new Position(Row.RANK4, Column.c),
                        new Position(Row.RANK4, Column.b),
                        new Position(Row.RANK4, Column.a)
                )
        );
    }
}
