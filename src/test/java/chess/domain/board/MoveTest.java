package chess.domain.board;

import chess.domain.location.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MoveTest {

    @DisplayName("두 개의 칸을 활용해 객체를 생성할 수 있다.")
    @Nested
    class ConstructTest {

        @DisplayName("대각선 방향을 생성할 수 있다.")
        @Test
        void diagonalDirectionTest() {
            Location source = Location.of("A1");
            Location target = Location.of("C3");

            Move move = Move.of(source, target);
            Assertions.assertThat(move).isEqualTo(new Move(Direction.UP_RIGHT, Direction.UP_RIGHT));
        }

        @DisplayName("수직 방향을 생성할 수 있다.")
        @Test
        void verticalDirectionTest() {
            Location source = Location.of("A1");
            Location target = Location.of("A3");

            Move move = Move.of(source, target);
            Assertions.assertThat(move).isEqualTo(new Move(Direction.UP, Direction.UP));
        }

        @DisplayName("수평 방향을 생성할 수 있다.")
        @Test
        void horizontalDirectionTest() {
            Location source = Location.of("A1");
            Location target = Location.of("C1");

            Move move = Move.of(source, target);
            Assertions.assertThat(move).isEqualTo(new Move(Direction.RIGHT, Direction.RIGHT));
        }

        @DisplayName("나이트 방향을 생성할 수 있다.")
        @Test
        void knightDirectionTest() {
            Location source = Location.of("A1");
            Location target = Location.of("B3");

            Move move = Move.of(source, target);
            Assertions.assertThat(move).isEqualTo(new Move(Direction.UP_RIGHT, Direction.UP));
        }
    }
}
