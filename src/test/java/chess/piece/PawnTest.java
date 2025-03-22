package chess.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @DisplayName("폰은 첫번째 순서에만 앞으로 두칸 갈 수 있다.")
    @Test
    void test1() {
        ChessBoard chessBoard = new ChessBoard(Map.of());
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position end = new Position(Column.B, Row.FOUR);

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }

    @DisplayName("폰은 첫번째가 아닐 땐 앞으로 두칸 갈 수 없다.")
    @Test
    void test2() {
        ChessBoard chessBoard = new ChessBoard(Map.of());
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position middle = new Position(Column.B, Row.FOUR);
        Position end = new Position(Column.B, Row.SIX);
        pawn.canMoveToDestination(chessBoard, start, middle);

        assertThat(pawn.canMoveToDestination(chessBoard, middle, end))
                .isFalse();
    }

    @DisplayName("폰은 공격할 때만 대각선으로 갈 수 있다.")
    @Test
    void test3() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.A, Row.THREE), new Pawn(Color.BLACK)
        ));
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position end = new Position(Column.A, Row.THREE);

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }

    @DisplayName("폰은 공격하지 않을 때는 대각선으로 갈 수 없다.")
    @Test
    void test4() {
        ChessBoard chessBoard = new ChessBoard(Map.of());
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position end = new Position(Column.A, Row.THREE);

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("폰은 비었을 때만 앞으로 한칸을 이동할 수 있다.")
    @Test
    void test5() {
        ChessBoard chessBoard = new ChessBoard(Map.of());
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position end = new Position(Column.B, Row.THREE);

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isTrue();
    }

    @DisplayName("폰은 앞으로 한칸을 이동할 때 공격할 수 없다.")
    @Test
    void test6() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position(Column.B, Row.THREE), new Pawn(Color.BLACK)
        ));
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(Column.B, Row.TWO);
        Position end = new Position(Column.B, Row.THREE);

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }

    @DisplayName("폰은 그 외의 방향으로는 움직일 수 없다.")
    @ParameterizedTest
    @CsvSource({
            "D, FOUR, D, SEVEN",
            "D, FOUR, D, THREE",
            "D, FOUR, C, FIVE",
    })
    void test9(Column startColumn, Row startRow, Column endColumn, Row endRow) {
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);
        ChessBoard chessBoard = new ChessBoard(Map.of());

        assertThat(pawn.canMoveToDestination(chessBoard, start, end))
                .isFalse();
    }
}