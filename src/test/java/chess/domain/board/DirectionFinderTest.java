package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DirectionFinderTest {
    @DisplayName("경로를 생성한다.")
    @Nested
    class DirectionsTest {
        @DisplayName("제자리 경로는 생성할 수 없다.")
        @Test
        void notMoveCreateExceptionTest() {
            assertThatCode(() -> DirectionFinder.find(
                    new Location(Column.C, Row.TWO), new Location(Column.C, Row.TWO))
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("제자리 경로를 생성할 수 없습니다.");
        }

        /*
        ........  8 (rank 8)
        ........  7
        ........  6
        ........  5
        ........  4
        ........  3
        ...D....  2
        S.......  1 (rank 1)
        abcdefgh
         */
        @DisplayName("오른쪽위, 오른쪽, 오른쪽 경로를 생성할 수 있다.")
        @Test
        void create_UR_R_R_Test() {
            List<Direction> directions = DirectionFinder.find(
                    new Location(Column.A, Row.ONE),
                    new Location(Column.D, Row.TWO)
            );
            assertThat(directions).containsExactly(
                    Direction.UP_RIGHT, Direction.RIGHT, Direction.RIGHT
            );
        }

        /*
        S.......  8 (rank 8)
        ........  7
        ........  6
        D.......  5
        ........  4
        ........  3
        ........  2
        ........  1 (rank 1)
        abcdefgh
         */
        @DisplayName("아래, 아래, 아래 경로를 생성할 수 있다.")
        @Test
        void create_D_D_D_Test() {
            List<Direction> directions = DirectionFinder.find(
                    new Location(Column.A, Row.EIGHT),
                    new Location(Column.A, Row.FIVE)
            );
            assertThat(directions).containsExactly(
                    Direction.DOWN, Direction.DOWN, Direction.DOWN
            );
        }
    }
}
