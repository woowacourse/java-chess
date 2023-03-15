package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class QueenTest {

    @DisplayName("퀸이 이동가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Queen queen = Queen.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(2, 2)
                );
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(3, 6);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(2, 7),
                    Location.of(3, 6)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 5);
                final Location end = Location.of(4, 8);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(6, 6),
                    Location.of(5, 7),
                    Location.of(4, 8)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(3, 3);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(6, 6),
                    Location.of(5, 5),
                    Location.of(4, 4),
                    Location.of(3, 3)
                );
            }),
            DynamicTest.dynamicTest("위로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(queen.searchPath(start, end)).containsExactly(end);
            }),
            DynamicTest.dynamicTest("아래로 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(1, 6);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(1, 7),
                    Location.of(1, 6)
                );
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(4, 7);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(6, 7),
                    Location.of(5, 7),
                    Location.of(4, 7)
                );
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 수 있다.", () -> {
                final Location start = Location.of(3, 7);
                final Location end = Location.of(7, 7);
                assertThat(queen.searchPath(start, end)).containsExactly(
                    Location.of(4, 7),
                    Location.of(5, 7),
                    Location.of(6, 7),
                    Location.of(7, 7)
                );
            })

        );
    }

    @DisplayName("퀸이 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Queen queen = Queen.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 2칸 오른쪽 1칸으로는 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 3);
                assertThatThrownBy(() -> queen.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래 2칸 왼쪽 1칸으로는 움직일 때 오류를 던진다.", () -> {
                final Location start = Location.of(2, 3);
                final Location end = Location.of(1, 1);
                assertThatThrownBy(() -> queen.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
