package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class PawnTest {

    @DisplayName("검은색 폰이 이동 가능한 경로일 때 true를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackMovable() {
        final Pawn pawn = Pawn.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("검은색이고 초기 위치에 있을 때, 아래로 2칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 5);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("검은색일 때, 아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 6);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("검은색일 때, 오른쪽 대각선 아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(2, 6);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("검은색일 때, 왼쪽 대각선 아래로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 7);
                final Location end = Location.of(1, 6);
                assertThat(pawn.movable(start, end)).isTrue();
            })
        );
    }

    @DisplayName("흰색 폰이 이동 가능한 경로일 때 true를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("흰색이고 초기 위치에 있을 때, 위로 2칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 4);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("흰색일 때, 위로 2칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 3);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("흰색일 때, 왼쪽 대각선 위로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(2, 2);
                final Location end = Location.of(1, 3);
                assertThat(pawn.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("흰색일 때, 오른쪽 대각선 위로 1칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(2, 3);
                assertThat(pawn.movable(start, end)).isTrue();
            })
        );
    }

    @DisplayName("검은색 폰이 이동 불가능한 경로일 때 false를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackNotMovable() {
        final Pawn pawn = Pawn.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("검은색이고 초기 위치에 있을 때, 아래로 3칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 4);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("검은색일 때, 위로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(1, 8);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("검은색일 때, 오른쪽 대각선 아래로 2칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 7);
                final Location end = Location.of(3, 5);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("검은색일 때, 왼쪽 대각선 아래로 2칸 움직일 수 없다.", () -> {
                final Location start = Location.of(3, 7);
                final Location end = Location.of(1, 5);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("검은색일 때, 왼쪽 대각선 위로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(3, 7);
                final Location end = Location.of(2, 8);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("검은색일 때, 오른쪽 대각선 위로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(3, 7);
                final Location end = Location.of(4, 8);
                assertThat(pawn.movable(start, end)).isFalse();
            })
        );
    }

    @DisplayName("흰색 폰이 이동 불가능한 경로일 때 false를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteNotMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("흰색이고 초기 위치에 있을 때, 위로 3칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 5);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("흰색일 때, 아래로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 1);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("흰색일 때, 왼쪽 대각선 위로 2칸 움직일 수 없다.", () -> {
                final Location start = Location.of(3, 4);
                final Location end = Location.of(1, 2);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("흰색일 때, 오른쪽 대각선 위로 2칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 4);
                final Location end = Location.of(3, 2);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("흰색일 때, 오른쪽 대각선 아래로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(1, 4);
                final Location end = Location.of(2, 3);
                assertThat(pawn.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("흰색일 때, 왼쪽 대각선 아래로 1칸 움직일 수 없다.", () -> {
                final Location start = Location.of(2, 4);
                final Location end = Location.of(1, 3);
                assertThat(pawn.movable(start, end)).isFalse();
            })
        );
    }
}
