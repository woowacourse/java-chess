package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Location;
import domain.Section;
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
            DynamicTest.dynamicTest("오른쪽 위로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(2, 2);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(2, 2)
                    );
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(3, 5);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(2, 6),
                        Location.of(3, 5)
                    );
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(7, 4);
                final Location endLocation = Location.of(4, 7);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(6, 5),
                        Location.of(5, 6),
                        Location.of(4, 7)
                    );
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(3, 3);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(6, 6),
                        Location.of(5, 5),
                        Location.of(4, 4),
                        Location.of(3, 3)
                    );
            }),
            DynamicTest.dynamicTest("위로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(1, 2);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(endLocation);
            }),
            DynamicTest.dynamicTest("아래로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(1, 7);
                final Location endLocation = Location.of(1, 5);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(1, 6),
                        Location.of(1, 5)
                    );
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(7, 7);
                final Location endLocation = Location.of(4, 7);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
                        Location.of(6, 7),
                        Location.of(5, 7),
                        Location.of(4, 7)
                    );
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직이는 경로 반환.", () -> {
                final Location startLocation = Location.of(3, 7);
                final Location endLocation = Location.of(7, 7);
                assertThat(
                    queen.searchPath(Section.of(startLocation, queen), Section.of(endLocation, EmptyPiece.make())))
                    .containsExactly(
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
                final Location startLocation = Location.of(1, 1);
                final Location endLocation = Location.of(2, 3);
                assertThatThrownBy(
                    () -> queen.searchPath(Section.of(startLocation, queen),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래 2칸 왼쪽 1칸으로는 움직일 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(2, 3);
                final Location endLocation = Location.of(1, 1);
                assertThatThrownBy(
                    () -> queen.searchPath(Section.of(startLocation, queen),
                        Section.of(endLocation, EmptyPiece.make())))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("도착지에 같은 편 말이 있을 때 오류를 던진다.", () -> {
                final Location startLocation = Location.of(3, 7);
                final Location endLocation = Location.of(7, 7);
                assertThatThrownBy(
                    () -> queen.searchPath(Section.of(startLocation, queen),
                        Section.of(endLocation, Pawn.makeBlack())))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
