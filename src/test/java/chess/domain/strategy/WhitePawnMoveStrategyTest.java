package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnMoveStrategyTest {

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * ......p.  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("화이트 폰은 시작 위치에서는 아래로 2칸을 움직일 수 있다.")
    void canMoveTwoDistanceAtStartPositionTest() {
        PieceType whitePawn = PieceType.WHITE_PAWN;

        Map<Direction, Queue<Position>> directionListMap = whitePawn.calculateAllDirectionPositions(
                new Position(Row.TWO, Column.G));

        assertAll(
                () -> assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.THREE, Column.G),
                        new Position(Row.FOUR, Column.G)),
                () -> assertThat(directionListMap.get(Direction.S)).isNull(),
                () -> assertThat(directionListMap.get(Direction.E)).isNull(),
                () -> assertThat(directionListMap.get(Direction.W)).isNull(),
                () -> assertThat(directionListMap.get(Direction.NE)).containsExactly(new Position(Row.THREE, Column.H)),
                () -> assertThat(directionListMap.get(Direction.NW)).containsExactly(new Position(Row.THREE, Column.F))
        );
    }

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * .......p  3
     * ........  2
     * ........  1 (rank1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("화이트 폰은 시작 위치가 아니면 위로 1칸만 움직일 수 있다.")
    void canMoveOneDistancePositionTest() {
        PieceType whitePawn = PieceType.WHITE_PAWN;

        Map<Direction, Queue<Position>> directionListMap = whitePawn.calculateAllDirectionPositions(
                new Position(Row.THREE, Column.H));

        assertAll(
                () -> assertThat(directionListMap.get(Direction.N)).containsExactly(
                        new Position(Row.FOUR, Column.H)
                )
        );
    }
}
