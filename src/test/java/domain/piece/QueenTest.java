package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class QueenTest {

    @DisplayName("퀸이 이동가능한 경로일 때 True를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Queen queen = Queen.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("아래로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(1, 1);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 7);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 7);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(8, 1);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 8);
                assertThat(queen.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 6);
                assertThat(queen.movable(start, end)).isTrue();
            })
        );
    }

    @DisplayName("퀸이 이동 불가능한 경로일 때 False를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Queen queen = Queen.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 2칸 오른쪽 1칸으로는 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 3);
                assertThat(queen.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("아래 2칸 왼쪽 1칸으로는 움직일 수 없다.", () -> {
                final Location start = Location.of(2, 3);
                final Location end = Location.of(1, 1);
                assertThat(queen.movable(start, end)).isFalse();
            })
        );
    }
}
