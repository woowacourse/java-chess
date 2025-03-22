package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @DisplayName("룩은 위로 움직일 수 있다.")
    @Test
    void test1() {
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(rook.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("룩은 아래로 움직일 수 있다.")
    @Test
    void test2() {
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(rook.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("룩은 왼쪽으로 움직일 수 있다.")
    @Test
    void test3() {
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(rook.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("룩은 오른쪽으로 움직일 수 있다.")
    @Test
    void test4() {
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(rook.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("룩은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "A, ONE, H, EIGHT",
            "A, ONE, C, TWO",
            "D, TWO, E, ONE",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(rook.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isFalse();
    }

    @DisplayName("룩은 가는 방향에 말이 있으면 움직일 수 없다.")
    @Test
    void test9() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.A, Row.FOUR), new King(Color.WHITE)
        ));
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(rook.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("룩은 목적지에 같은 편의 말이 있으면 움직일 수 없다.")
    @Test
    void test10() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.A, Row.FOUR), new King(Color.WHITE)
        ));
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.FOUR);

        Assertions.assertThat(rook.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("룩은 목적지에 상대 편의 말이 있으면 움직일 수 있다.")
    @Test
    void test11() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.A, Row.FOUR), new King(Color.BLACK)
        ));
        Rook rook = new Rook(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.FOUR);

        Assertions.assertThat(rook.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }
}