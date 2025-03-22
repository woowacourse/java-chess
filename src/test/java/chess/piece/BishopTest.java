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

class BishopTest {
    @DisplayName("비숍은 위+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test5() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(bishop.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("비숍은 위+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test6() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(bishop.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("비숍은 아래+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test7() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(bishop.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("비숍은 아래+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test8() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.H, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(bishop.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isTrue();
    }

    @DisplayName("비숍은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "H, EIGHT, H, ONE",
            "F, FIVE, G, FIVE",
            "A, ONE, A, TWO",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(bishop.canMoveToDestination(new ChessBoard(Map.of()), start, end))
                .isFalse();
    }

    @DisplayName("비숍은 가는 방향에 말이 있으면 움직일 수 없다.")
    @Test
    void test9() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.D, Row.FOUR), new King(Color.WHITE)
        ));
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(bishop.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("비숍은 목적지에 같은 편의 말이 있으면 움직일 수 없다.")
    @Test
    void test10() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.H, Row.EIGHT), new King(Color.WHITE)
        ));
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(bishop.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("비숍은 목적지에 상대 편의 말이 있으면 움직일 수 있다.")
    @Test
    void test11() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.H, Row.EIGHT), new King(Color.BLACK)
        ));
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(bishop.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }
}