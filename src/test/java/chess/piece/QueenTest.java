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

class QueenTest {

    @DisplayName("퀸은 위로 움직일 수 있다.")
    @Test
    void test1() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래로 움직일 수 있다.")
    @Test
    void test2() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 왼쪽으로 움직일 수 있다.")
    @Test
    void test3() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 오른쪽으로 움직일 수 있다.")
    @Test
    void test4() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 위+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test5() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 위+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test6() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test7() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test8() {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.H, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("퀸은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "H, EIGHT, G, SIX",
            "F, FIVE, H, FOUR",
            "A, ONE, D, TWO",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(queen.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isFalse();
    }

    @DisplayName("퀸은 가는 방향에 말이 있으면 움직일 수 없다.")
    @Test
    void test9() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.D, Row.ONE), new King(Color.WHITE)
        ));
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("퀸은 목적지에 같은 편의 말이 있으면 움직일 수 없다.")
    @Test
    void test10() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.H, Row.ONE), new King(Color.WHITE)
        ));
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("퀸은 목적지에 상대 편의 말이 있으면 움직일 수 있다.")
    @Test
    void test11() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.H, Row.ONE), new King(Color.BLACK)
        ));
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }
}