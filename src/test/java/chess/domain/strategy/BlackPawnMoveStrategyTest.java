package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.position.Column;
import chess.domain.board.position.Direction;
import chess.domain.piece.PieceType;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import java.util.Map;
import java.util.Queue;
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

        Map<Direction, Queue<Position>> directionListMap = blackPawn.generateAllDirectionPositions(
                new Position(Row.SEVEN, Column.G));

        assertAll(
                () -> assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.SIX, Column.G),
                        new Position(Row.FIVE, Column.G)),
                () -> assertThat(directionListMap.get(Direction.N)).isNull(),
                () -> assertThat(directionListMap.get(Direction.E)).isNull(),
                () -> assertThat(directionListMap.get(Direction.W)).isNull(),
                () -> assertThat(directionListMap.get(Direction.SE)).containsExactly(new Position(Row.SIX, Column.H)),
                () -> assertThat(directionListMap.get(Direction.SW)).containsExactly(new Position(Row.SIX, Column.F))
        );
    }

    /**
     * ........  8 (rank 8) ........  7 .......P  6 ........  5 ........  4 ........  3 ........  2 ........  1 (rank
     * 1)
     * <p>
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰은 시작 위치가 아니면 아래로 1칸만 움직일 수 있다.")
    void canMoveOneDistancePositionTest() {
        PieceType blackPawn = PieceType.BLACK_PAWN;

        Map<Direction, Queue<Position>> directionListMap = blackPawn.generateAllDirectionPositions(
                new Position(Row.SIX, Column.H));

        assertAll(
                () -> assertThat(directionListMap.get(Direction.S)).containsExactly(
                        new Position(Row.FIVE, Column.H)
                ))
        ;
    }
}
