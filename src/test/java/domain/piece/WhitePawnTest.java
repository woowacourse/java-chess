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

class WhitePawnTest {

    @DisplayName("시작 위치의 폰이 아니면 2칸 위로 이동할 수 없다.")
    @Test
    void cantMoveWhenNotInitialPawn() {
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination = new Coordinate(1, 3);
        WhitePawn whitePawn = new WhitePawn();

        assertThatThrownBy(() -> whitePawn.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("아래 및 뒤로는 이동할 수 없다.")
    @ParameterizedTest(name = "{1}")
    @MethodSource("provideWhitePawnInvalidDirection")
    void notMovableDirection(Coordinate destination, String direction) {
        Coordinate start = new Coordinate(5, 5);

        WhitePawn whitePawn = new WhitePawn();

        assertThatThrownBy(() -> whitePawn.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @Nested
    @DisplayName("화이트 폰이 이동할 수 있는 방향을 확인한다.")
    class GetDirectionTest {
        WhitePawn whitePawn = new WhitePawn();
        Coordinate start;
        Coordinate destination;

        @DisplayName("위로 이동할 수 있다.")
        @Test
        void moveToDown() {
            start = new Coordinate(3, 1);
            destination = new Coordinate(2, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(StraightDirection.UP);
        }

        @DisplayName("시작 위치의 폰은 위로 두 칸 이동할 수 있다.")
        @Test
        void moveToDownWhenInitialPawn() {
            start = new Coordinate(6, 1);
            destination = new Coordinate(4, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(StraightDirection.UP);
        }

        @DisplayName("왼쪽 위 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownLeftDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(2, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_LEFT);
        }

        @DisplayName("오른쪽 위 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownRightDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(2, 3);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_RIGHT);
        }
    }

    // 흰색 폰이 (5, 5)에 있다고 가정하고, 이동할 수 없는 방향을 반환한다.
    private static Stream<Arguments> provideWhitePawnInvalidDirection() {
        return Stream.of(
                Arguments.arguments(new Coordinate(6, 5), "아래"),
                Arguments.arguments(new Coordinate(3, 5), "시작 폰이 아닐 때 위로 두 칸"),
                Arguments.arguments(new Coordinate(5, 4), "왼쪽"),
                Arguments.arguments(new Coordinate(5, 6), "오른쪽"),
                Arguments.arguments(new Coordinate(6, 6), "오른쪽 아래 대각선"),
                Arguments.arguments(new Coordinate(6, 4), "왼쪽 아래 대각선")
        );
    }
}
