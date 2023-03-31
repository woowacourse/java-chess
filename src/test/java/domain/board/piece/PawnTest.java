package domain.board.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import domain.path.Path;
import domain.path.PieceMove;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class PawnTest {

    @DisplayName("검은색 이동 가능한 경로인지를 검증한다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackMovable() {
        final Piece blackPawn = Piece.blackPawn();
        return Stream.of(
                DynamicTest.dynamicTest("아래로 1칸 움직일 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.empty()));
                    Assertions.assertDoesNotThrow(() -> blackPawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("초기 상태일 때 아래로 2칸 움직일 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(4), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.empty()));
                    Assertions.assertDoesNotThrow(() -> blackPawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("오른쪽 아래로 공격할 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(2));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.whitePawn()));
                    Assertions.assertDoesNotThrow(() -> blackPawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("왼쪽 아래로 공격할 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(2));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.whitePawn()));
                    Assertions.assertDoesNotThrow(() -> blackPawn.validatePath(path));
                })
        );
    }

    @DisplayName("검은색 폰이 이동 불가능한 경로일 때 오류를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsBlackNotMovable() {
        final Piece blackPawn = Piece.blackPawn();
        return Stream.of(
                DynamicTest.dynamicTest("위로 1칸 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.empty()));
                    assertThatThrownBy(() -> blackPawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                }),
                DynamicTest.dynamicTest("오른쪽 아래로 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(2));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.empty()));
                    assertThatThrownBy(() -> blackPawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                }),
                DynamicTest.dynamicTest("왼쪽 아래로 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(2));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(blackPawn, Piece.empty()));
                    assertThatThrownBy(() -> blackPawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                })
        );
    }

    @DisplayName("하얀색 폰이 이동 가능한 경로인지를 검증한다..")
    @TestFactory
    Stream<DynamicTest> testIsWhiteMovable() {
        final Piece whitePawn = Piece.whitePawn();
        return Stream.of(
                DynamicTest.dynamicTest("위로 1칸 움직일 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.empty()));
                    Assertions.assertDoesNotThrow(() -> whitePawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("초기 상태일 때 위로 2칸 움직일 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(3), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.empty()));
                    Assertions.assertDoesNotThrow(() -> whitePawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("오른쪽 위로 공격할 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(2));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.blackPawn()));
                    Assertions.assertDoesNotThrow(() -> whitePawn.validatePath(path));
                }),
                DynamicTest.dynamicTest("왼쪽 위로 공격할 수 있다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(2));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.blackPawn()));
                    Assertions.assertDoesNotThrow(() -> whitePawn.validatePath(path));
                })
        );
    }

    @DisplayName("하얀색 폰이 이동 불가능한 경로일 때 오류를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsWhiteNotMovable() {
        final Piece whitePawn = Piece.whitePawn();
        return Stream.of(
                DynamicTest.dynamicTest("아래로 1칸 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(7), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(6), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.empty()));
                    assertThatThrownBy(() -> whitePawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                }),
                DynamicTest.dynamicTest("오른쪽 위로 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(1));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(2));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.empty()));
                    assertThatThrownBy(() -> whitePawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                }),
                DynamicTest.dynamicTest("왼쪽 위로 움직일 수 없다.", () -> {
                    final Location start = Location.of(Row.valueOf(6), Column.valueOf(2));
                    final Location end = Location.of(Row.valueOf(7), Column.valueOf(1));
                    Path path = new Path(new PieceMove(start, end), List.of(whitePawn, Piece.empty()));
                    assertThatThrownBy(() -> whitePawn.validatePath(path))
                            .isInstanceOf(IllegalArgumentException.class);
                })
        );
    }
}
