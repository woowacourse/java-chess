package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class RookTest {

    @DisplayName("룩이 이동가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Rook rook = Rook.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직이는 경로를 반환한다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(rook.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("아래로 움직이는 경로를 반환한다", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 5);
                assertThat(rook.searchPath(start, end)).containsExactly(
                    Location.of(1, 6),
                    Location.of(1, 5)
                );
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직이는 경로를 반환한다", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(4, 7);
                assertThat(rook.searchPath(start, end)).containsExactly(
                    Location.of(6, 7),
                    Location.of(5, 7),
                    Location.of(4, 7)
                );
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직이는 경로를 반환한다", () -> {
                final Location start = Location.of(3, 7);
                final Location end = Location.of(7, 7);
                assertThat(rook.searchPath(start, end)).containsExactly(
                    Location.of(4, 7),
                    Location.of(5, 7),
                    Location.of(6, 7),
                    Location.of(7, 7)
                );
            })
        );
    }

    @DisplayName("루크가 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Rook rook = Rook.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위로 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThatThrownBy(() -> rook.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(7, 1);
                assertThatThrownBy(() -> rook.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(7, 6);
                final Location end = Location.of(6, 7);
                assertThatThrownBy(() -> rook.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 6);
                assertThatThrownBy(() -> rook.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
