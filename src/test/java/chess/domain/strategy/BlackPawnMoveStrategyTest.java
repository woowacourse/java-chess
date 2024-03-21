package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Deque;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPawnMoveStrategyTest {

    /**
     * ........  8 (rank 8)
     * ......P.  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰은 시작 위치에서는 아래로 2칸을 움직일 수 있다.")
    void canMoveTwoDistanceAtStartPositionTest() {
        PieceType blackPawn = PieceType.BLACK_PAWN;

        Map<Direction, Deque<Position>> directionListMap = blackPawn.calculateAllDirectionPositions(
                new Position(Row.RANK7, Column.G));

        assertAll(
            () -> assertThat(directionListMap.get(Direction.S)).containsExactly(
                    new Position(Row.RANK6, Column.G),
                    new Position(Row.RANK5, Column.G)),
            () -> assertThat(directionListMap.get(Direction.N)).isNull(),
            () -> assertThat(directionListMap.get(Direction.E)).isNull(),
            () -> assertThat(directionListMap.get(Direction.W)).isNull(),
            () -> assertThat(directionListMap.get(Direction.SE)).containsExactly(new Position(Row.RANK6, Column.H)),
            () -> assertThat(directionListMap.get(Direction.SW)).containsExactly(new Position(Row.RANK6, Column.F))
        );
    }

    /**
     * ........  8 (rank 8)
     * ........  7
     * .......P  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰은 시작 위치가 아니면 아래로 1칸만 움직일 수 있다.")
    void canMoveOneDistancePositionTest() {
        PieceType blackPawn = PieceType.BLACK_PAWN;

        Map<Direction, Deque<Position>> directionListMap = blackPawn.calculateAllDirectionPositions(
                new Position(Row.RANK6, Column.H));

        assertAll(
                () -> assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.RANK5, Column.H)
                ))
        ;
    }
}
