package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.StraightDirection;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackPawnTest {

    @DisplayName("시작 위치의 폰이 아니면 2칸 아래로 이동할 수 없다.")
    @Test
    void cantMoveWhenNotInitialPawn() {
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination = new Coordinate(5, 3);
        BlackPawn blackPawn = new BlackPawn();

        assertThatThrownBy(() -> blackPawn.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("올바르지 않은 방향으로 이동할 수 없다.")
    @ParameterizedTest(name = "{1}")
    @MethodSource("provideBlackPawnInvalidDirection")
    void notMovableDirection(Coordinate destination, String direction) {
        Coordinate start = new Coordinate(2, 2);

        BlackPawn blackPawn = new BlackPawn();

        assertThatThrownBy(() -> blackPawn.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @Nested
    @DisplayName("블랙 폰이 이동할 수 있는 방향을 확인한다.")
    class GetDirectionTest {
        BlackPawn blackPawn = new BlackPawn();
        Coordinate start;
        Coordinate destination;

        @DisplayName("아래로 이동할 수 있다.")
        @Test
        void moveToDown() {
            start = new Coordinate(3, 1);
            destination = new Coordinate(4, 1);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(StraightDirection.DOWN);
        }

        @DisplayName("시작 위치의 폰은 2칸 아래로 이동할 수 있다.")
        @Test
        void cantMoveWhenInitialPawn() {
            start = new Coordinate(1, 3);
            destination = new Coordinate(3, 3);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(StraightDirection.DOWN);
        }

        @DisplayName("왼쪽 아래 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownLeftDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(4, 1);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_LEFT);
        }

        @DisplayName("오른쪽 아래 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownRightDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(4, 3);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_RIGHT);
        }
    }

    // 흑색 폰이 (2, 2)에 있다고 가정하고, 이동할 수 없는 방향을 반환한다.
    private static Stream<Arguments> provideBlackPawnInvalidDirection() {
        return Stream.of(
                Arguments.arguments(new Coordinate(1, 2), "위"),
                Arguments.arguments(new Coordinate(4, 2), "시작 폰이 아닐 때 아래로 두 칸"),
                Arguments.arguments(new Coordinate(2, 1), "왼쪽"),
                Arguments.arguments(new Coordinate(2, 3), "오른쪽"),
                Arguments.arguments(new Coordinate(1, 3), "오른쪽 위 대각선"),
                Arguments.arguments(new Coordinate(1, 1), "왼쪽 위 대각선")
        );
    }
}
