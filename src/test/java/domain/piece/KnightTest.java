package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class KnightTest {

    @DisplayName("나이트의 이동 경로는 도착지점이다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Knight knight = Knight.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 2칸 오른쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 2);
                final Location end = Location.of(3, 4);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("위로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 2);
                final Location end = Location.of(1, 4);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("아래로 2칸 오른쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(4, 1);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("아래로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(2, 1);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(5, 2);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(5, 4);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(1, 2);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 3);
                final Location end = Location.of(1, 4);
                assertThat(knight.searchPath(start, end)).containsExactly(end);
            })
        );
    }

    @DisplayName("나이트가 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Knight knight = Knight.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(1, 1);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 7);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 7);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(8, 1);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 8);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 6);
                assertThatThrownBy(() -> knight.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
