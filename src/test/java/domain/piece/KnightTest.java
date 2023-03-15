package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class KnightTest {

    @DisplayName("나이트가 이동 가능한 경로일 때 True를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Knight knight = Knight.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 2칸 오른쪽 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 2);
                final Location end = Location.of(3, 4);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("위로 2칸 왼쪽 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 2);
                final Location end = Location.of(1, 4);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("아래로 2칸 오른쪽 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(4, 1);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("아래로 2칸 왼쪽 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(2, 1);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 아래로 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(5, 2);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 위로 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(5, 4);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 아래로 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(1, 2);
                assertThat(knight.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 위로 1칸으로는 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(1, 4);
                assertThat(knight.movable(start, end)).isTrue();
            })
        );
    }

    @DisplayName("나이트가 이동가능하지 않은 경로일 때 False를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Knight knight = Knight.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("아래로 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(1, 1);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 수 없다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 7);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 수 없다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 7);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향으로 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향으로 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(8, 1);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향으로 움직일 수 없다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 8);
                assertThat(knight.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향으로 움직일 수 없다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 6);
                assertThat(knight.movable(start, end)).isFalse();
            })
        );
    }
}
