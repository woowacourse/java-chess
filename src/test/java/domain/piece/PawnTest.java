package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class PawnTest {

    @DisplayName("검은색 폰이 이동 가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackMovable() {
        final Pawn pawn = Pawn.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 6);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(1, 6)
                );
            }),
            DynamicTest.dynamicTest("초기 상태일 때 아래로 2칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 6);
                final Location end = Location.of(1, 4);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(1, 5),
                    Location.of(1, 4)
                );
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(2, 6);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(2, 6)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(0, 6);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(0, 6)
                );
            })
        );
    }

    @DisplayName("검은색 폰이 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackNotMovable() {
        final Pawn pawn = Pawn.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("아래로 3칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 6);
                final Location end = Location.of(1, 3);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위로 1칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 5);
                final Location end = Location.of(1, 6);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위로 2칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 5);
                final Location end = Location.of(1, 7);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("초기 상태가 아닐 때 아래로 2칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 5);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 5);
                final Location end = Location.of(2, 6);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 5);
                final Location end = Location.of(0, 6);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }

    @DisplayName("하얀색 폰이 이동 가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("위로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(1, 2)
                );
            }),
            DynamicTest.dynamicTest("초기 상태일 때 위로 2칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 3);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(1, 2),
                    Location.of(1, 3)
                );
            }),
            DynamicTest.dynamicTest("오른쪽 위로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(2, 2)
                );
            }),
            DynamicTest.dynamicTest("왼쪽 위로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(0, 2);
                assertThat(pawn.searchPath(start, end)).containsExactly(
                    Location.of(0, 2)
                );
            })
        );
    }

    @DisplayName("하얀색 폰이 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteNotMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("위로 3칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(1, 6);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 1칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(1, 2);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 2칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(1, 1);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("초기 상태가 아닐 때 위로 2칸 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 4);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(2, 2);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(0, 2);
                assertThatThrownBy(() -> pawn.searchPath(start, end))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
