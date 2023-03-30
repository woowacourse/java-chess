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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class RookTest {

    @DisplayName("룩이 이동 가능한 경로인지를 검증한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Piece rook = Piece.blackRook();
        return Stream.of(
            DynamicTest.dynamicTest("위로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> rook.validatePath(path));
            }),
            DynamicTest.dynamicTest("아래로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(0));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> rook.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(6), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> rook.validatePath(path));
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 때 검증", () -> {
                final Location start = Location.of(Row.valueOf(6), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(7), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> rook.validatePath(path));
            })
        );
    }

    @DisplayName("룩이 이동 불가능한 경로일 때 오류를 반환한다.")
    @TestFactory
    Stream<DynamicTest> testIsNotMovable() {
        final Piece rook = Piece.blackRook();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                assertThatThrownBy(() -> rook.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("오른쪽 아래로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(5));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                assertThatThrownBy(() -> rook.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 위로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(5));
                final Location end = Location.of(Row.valueOf(5), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                assertThatThrownBy(() -> rook.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            }),
            DynamicTest.dynamicTest("왼쪽 아래로 움직일 경우 오류를 반환.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(3), Column.valueOf(3));
                Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.empty()));
                assertThatThrownBy(() -> rook.validatePath(path))
                    .isInstanceOf(IllegalArgumentException.class);
            })
        );
    }

    @DisplayName("룩이 이동할 곳에 같은 진영의 기물이 존재할 경우 오류를 던진다.")
    @Test
    void sameCampPieceExistInDestination() {
        final Piece rook = Piece.blackRook();
        final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
        final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
        Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.blackPawn()));
        assertThatThrownBy(() -> rook.validatePath(path))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩이 이동하는 경로에 기물이 존재할 경우 오류를 던진다.")
    @Test
    void pathIsBlocked() {
        final Piece rook = Piece.blackRook();
        final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
        final Location end = Location.of(Row.valueOf(1), Column.valueOf(3));
        Path path = new Path(new PieceMove(start, end), List.of(rook, Piece.blackPawn(), Piece.empty()));
        assertThatThrownBy(() -> rook.validatePath(path))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
