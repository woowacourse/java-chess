package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import domain.Section;
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
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(1, 6);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(1, 6)
                    );
            }),
            DynamicTest.dynamicTest("초기 위치일 때, 아래로 2칸 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(1, 5);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn),
                        Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(1, 6),
                        Location.of(1, 5)
                    );
            }),
            DynamicTest.dynamicTest("대각선에 적이 있을 경우 대각선으로 한 칸 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(2, 6);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, Pawn.makeWhite())))
                    .containsExactly(
                        Location.of(2, 6)
                    );
            })
        );
    }

    @DisplayName("흰색 폰이 이동 가능한 경로를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("위로 1칸 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(1, 2);
                final Location endLocation = Location.of(1, 3);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn),
                        Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                    Location.of(1, 3)
                );
            }),
            DynamicTest.dynamicTest("초기 위치일 때, 위로 2칸 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(1, 2);
                final Location endLocation = Location.of(1, 4);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn),
                        Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                    Location.of(1, 3),
                    Location.of(1, 4)
                );
            }),
            DynamicTest.dynamicTest("대각선에 적이 있을 경우 대각선으로 한 칸 움직일 수 있다.", () -> {
                final Location startLocation = Location.of(2, 6);
                final Location endLocation = Location.of(1, 7);
                assertThat(
                    pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, Pawn.makeBlack())))
                    .containsExactly(
                        Location.of(1, 7)
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
                final Location startLocation = Location.of(1, 6);
                final Location endLocation = Location.of(1, 3);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("초기 위치가 아닌 상태에서 아래로 2칸 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 6);
                final Location endLocation = Location.of(1, 4);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위로 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 3);
                final Location endLocation = Location.of(1, 4);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래 대각선에 같은 편 말이 있고 이동하려고 할 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 3);
                final Location endLocation = Location.of(2, 4);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, Pawn.makeBlack())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래 대각선이 비어있고 이동하려고 할 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 3);
                final Location endLocation = Location.of(2, 4);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }

    @DisplayName("흰색 폰이 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteNotMovable() {
        final Pawn pawn = Pawn.makeWhite();
        return Stream.of(
            DynamicTest.dynamicTest("위로 3칸 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 3);
                final Location endLocation = Location.of(1, 6);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("초기 위치가 아닌 상태에서 위로 2칸 움직이면 오류를 던진다.", () -> {
                final Location startLocation = Location.of(1, 6);
                final Location endLocation = Location.of(1, 8);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 움직이면 오류를 던진다..", () -> {
                final Location startLocation = Location.of(1, 4);
                final Location endLocation = Location.of(1, 3);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위 대각선에 같은 편 말이 있고 이동하려고 할 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(2, 4);
                final Location endLocation = Location.of(1, 3);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, Pawn.makeWhite())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위 대각선이 비어있고 이동하려고 할 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(2, 4);
                final Location endLocation = Location.of(1, 3);
                assertThatThrownBy(
                    () -> pawn.searchPath(Section.of(startLocation, pawn), Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
