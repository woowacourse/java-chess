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

class KingTest {

    @DisplayName("킹은 위로 한칸 움직일 수 있다.")
    @Test
    void test1() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.D, Row.THREE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 아래로 한칸 움직일 수 있다.")
    @Test
    void test2() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.D, Row.ONE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 왼쪽으로 한칸 움직일 수 있다.")
    @Test
    void test3() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.C, Row.TWO);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 오른쪽으로 한칸 움직일 수 있다.")
    @Test
    void test4() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.E, Row.TWO);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 위+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test5() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.E, Row.THREE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 위+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test6() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.C, Row.THREE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 아래+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test7() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.E, Row.ONE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 아래+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test8() {
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.C, Row.ONE);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("킹은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "D, TWO, G, SIX",
            "D, TWO, H, FOUR",
            "D, TWO, D, TWO",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        King king = new King(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(king.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isFalse();
    }

    @DisplayName("킹은 목적지에 같은 편의 말이 있으면 움직일 수 없다.")
    @Test
    void test10() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.E, Row.TWO), new King(Color.WHITE)
        ));
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.E, Row.TWO);

        Assertions.assertThat(king.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("킹은 목적지에 상대 편의 말이 있으면 움직일 수 있다.")
    @Test
    void test11() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.E, Row.TWO), new King(Color.BLACK)
        ));
        King king = new King(Color.WHITE);
        Position start = new Position(Column.D, Row.TWO);
        Position end = new Position(Column.E, Row.TWO);

        Assertions.assertThat(king.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }
}