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

class KingTest {

    @DisplayName("킹이 이동 가능한 경로인지를 검증한다.")
    @TestFactory
    Stream<DynamicTest> testIsMovable() {
        final Piece king = Piece.blackKing();
        return Stream.of(
            DynamicTest.dynamicTest("오른쪽 위 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("오른쪽 아래 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(2), Column.valueOf(6));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽 위 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(5));
                final Location end = Location.of(Row.valueOf(6), Column.valueOf(6));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽 아래 대각선 방향 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(6), Column.valueOf(6));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("위로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("아래로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(1), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(1), Column.valueOf(6));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("왼쪽으로 움직일 때 검증.", () -> {
                final Location start = Location.of(Row.valueOf(7), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(6), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            }),
            DynamicTest.dynamicTest("오른쪽으로 움직일 때 검증", () -> {
                final Location start = Location.of(Row.valueOf(6), Column.valueOf(7));
                final Location end = Location.of(Row.valueOf(7), Column.valueOf(7));
                Path path = new Path(new PieceMove(start, end), List.of(king, Piece.empty()));
                Assertions.assertDoesNotThrow(() -> king.validatePath(path));
            })
        );
    }

    @DisplayName("킹이 이동할 곳에 같은 진영의 기물이 존재할 경우 오류를 던진다.")
    @Test
    void sameCampPieceExistInDestination() {
        final Piece king = Piece.blackKing();
        final Location start = Location.of(Row.valueOf(1), Column.valueOf(1));
        final Location end = Location.of(Row.valueOf(1), Column.valueOf(2));
        Path path = new Path(new PieceMove(start, end), List.of(king, Piece.blackPawn()));
        assertThatThrownBy(() -> king.validatePath(path))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
