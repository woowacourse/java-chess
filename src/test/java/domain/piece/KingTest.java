package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Location;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class KingTest {

    @DisplayName("킹이 이동가능한 경로일 때 True를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final King king = King.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 2);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("아래로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 2);
                final Location end = Location.of(1, 1);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 7);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 7);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(2, 2);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(2, 7);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(6, 8);
                assertThat(king.movable(start, end)).isTrue();
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향으로 한칸 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(8, 6);
                assertThat(king.movable(start, end)).isTrue();
            })
        );
    }

    @DisplayName("킹이 이동 불가능한 경로일 때 False를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final King king = King.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(1, 3);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("아래로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 3);
                final Location end = Location.of(1, 1);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(7, 7);
                final Location end = Location.of(5, 7);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(6, 7);
                final Location end = Location.of(8, 7);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 1);
                final Location end = Location.of(3, 3);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(1, 8);
                final Location end = Location.of(3, 6);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(6, 6);
                final Location end = Location.of(4, 8);
                assertThat(king.movable(start, end)).isFalse();
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향으로 두칸 움직일 수 있다.", () -> {
                final Location start = Location.of(6, 6);
                final Location end = Location.of(4, 4);
                assertThat(king.movable(start, end)).isFalse();
            })
        );
    }
}
