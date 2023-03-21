package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import domain.Section;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class KingTest {

    @DisplayName("킹이 이동가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final King king = King.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(1, 2);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("아래로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 2);
                final Location endLocation = Location.of(1, 1);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("왼쪽으로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(6, 7);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("오른쪽으로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(6, 7);
                final Location endLocation = Location.of(7, 7);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("오른쪽 위로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(2, 2);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(2, 6);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 한칸 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(7, 6);
                final Location endLocation = Location.of(6, 7);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 한칸 움직이는 경로 반환..", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(6, 6);
                assertThat(king.searchPath(Section.of(startLocation, king),
                    Section.of(endLocation, king))).containsExactly(endLocation);
            })
        );
    }

    @DisplayName("킹이 이동 불가능한 경로일 때 오류를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final King king = King.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(1, 3);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(1, 3);
                final Location endLocation = Location.of(1, 1);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽으로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(5, 7);
                assertThatThrownBy(() -> king.searchPath(
                    Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽으로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(5, 7);
                final Location endLocation = Location.of(7, 7);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 위로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(3, 3);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(3, 5);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(6, 5);
                final Location endLocation = Location.of(4, 7);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 두칸 움직일 경우 오류를 반환.", () -> {
                final Location startLocation = Location.of(6, 6);
                final Location endLocation = Location.of(4, 4);
                assertThatThrownBy(
                    () -> king.searchPath(Section.of(startLocation, king), Section.of(endLocation, king)))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
