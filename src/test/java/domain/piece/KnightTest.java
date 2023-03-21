package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import domain.Section;
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
                final Location startLocation = Location.of(2, 2);
                final Location endLocation = Location.of(3, 4);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("위로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(2, 2);
                final Location endLocation = Location.of(1, 4);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("아래로 2칸 오른쪽 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(4, 1);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("아래로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(2, 1);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(5, 2);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(5, 4);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(1, 2);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(3, 3);
                final Location endLocation = Location.of(1, 4);
                assertThat(knight.searchPath(Section.of(startLocation, EmptyPiece.make()),
                    Section.of(endLocation, knight))).containsExactly(endLocation);
            })
        );
    }

    @DisplayName("나이트가 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Knight knight = Knight.makeBlack();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(1, 2);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(1, 1);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(6, 7);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(6, 7);
                final Location endLocation = Location.of(7, 7);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(2, 2);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(7, 1);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(7, 6);
                final Location endLocation = Location.of(6, 7);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(6, 6);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, knight),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("적절한 위치에 같은 편 말이 있을 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(6, 6);
                assertThatThrownBy(
                    () -> knight.searchPath(Section.of(startLocation, Pawn.makeWhite()),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
