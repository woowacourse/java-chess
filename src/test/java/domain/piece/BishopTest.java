package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class BishopTest {

    @DisplayName("비숍이 이동가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Bishop bishop = Bishop.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향 경로 반환.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(bishop.searchPath(start, end)).containsExactly(
                    Location.of(2, 2)
                );
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향 경로 반환.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(3, 5);
                assertThat(bishop.searchPath(start, end)).containsExactly(
                    Location.of(2, 6),
                    Location.of(3, 5)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향 경로 반환.", () -> {
                final Location start = Location.of(7, 5);
                final Location end = Location.of(5, 7);
                assertThat(bishop.searchPath(start, end)).containsExactly(
                    Location.of(6, 6),
                    Location.of(5, 7)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향 경로 반환.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(3, 3);
                assertThat(bishop.searchPath(start, end)).containsExactly(
                    Location.of(6, 6),
                    Location.of(5, 5),
                    Location.of(4, 4),
                    Location.of(3, 3)
                );
            })
        );
    }

    @DisplayName("비숍이 이동 불가능한 경로일 때 오류를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Bishop bishop = Bishop.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThatThrownBy(() -> bishop.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(1, 1);
                assertThatThrownBy(() -> bishop.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 7);
                assertThatThrownBy(() -> bishop.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(6, 7);
                final Location end = Location.of(7, 7);
                assertThatThrownBy(() -> bishop.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
