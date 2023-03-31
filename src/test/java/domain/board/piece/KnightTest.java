package domain.board.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.path.Path;
import domain.path.PieceMove;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class KnightTest {

    @DisplayName("나이트가 이동 가능한 경로인지 검증한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Piece knight = Piece.blackKnight();
        return Stream.of(
            DynamicTest.dynamicTest("위로 2칸 오른쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(4));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("위로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("아래로 2칸 오른쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(3), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(4));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("아래로 2칸 왼쪽 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(3), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(2), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(5));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("오른쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(5));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 아래로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(0), Column.valueOf(1));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽 2칸 위로 1칸으로 움직일 수 있다.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(3));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(1));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> knight.validatePath(path));
            })
        );
    }

    @DisplayName("나이트가 이동 불가능한 경로일 때 오류를 던진다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Piece knight = Piece.blackKnight();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(5));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(5));
                final Location end = Location.of(Row.valueOf(5), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(3));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("위로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("아래로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(0));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(6), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 때 검증", () -> {
                final Location start = Location.of(Row.valueOf(6), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(7), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(knight, Piece.empty()));
                assertThatThrownBy(() -> knight.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }
}
