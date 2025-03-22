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

class KnightTest {

    @DisplayName("나이트은 위,위 오른쪽으로 움직일 수 있다.")
    @Test
    void test1() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.E, Row.SIX);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 위,위 왼쪽으로 움직일 수 있다.")
    @Test
    void test2() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.C, Row.SIX);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 위,오른쪽 오른쪽으로 움직일 수 있다.")
    @Test
    void test3() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.F, Row.FIVE);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 위,왼쪽,왼쪽으로 움직일 수 있다.")
    @Test
    void test4() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.B, Row.FIVE);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 아래,아래,오른쪽으로 움직일 수 있다.")
    @Test
    void test5() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.E, Row.TWO);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 아래,아래,왼쪽 움직일 수 있다.")
    @Test
    void test6() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.C, Row.TWO);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 오른쪽,오른쪽,아래로 움직일 수 있다.")
    @Test
    void test7() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.F, Row.THREE);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 왼쪽,왼쪽,아래로 움직일 수 있다.")
    @Test
    void test8() {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.B, Row.THREE);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("나이트은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "D, FOUR, E, FIVE",
            "D, FOUR, F, FOUR",
            "D, FOUR, A, ONE",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(knight.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isFalse();
    }

    @DisplayName("나이트은 목적지에 같은 편의 말이 있으면 움직일 수 없다.")
    @Test
    void test10() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.E, Row.SIX), new Knight(Color.WHITE)
        ));
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.E, Row.SIX);

        Assertions.assertThat(knight.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("나이트은 목적지에 상대 편의 말이 있으면 움직일 수 있다.")
    @Test
    void test11() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.E, Row.SIX), new Knight(Color.BLACK)
        ));
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position(Column.D, Row.FOUR);
        Position end = new Position(Column.E, Row.SIX);

        Assertions.assertThat(knight.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }
}