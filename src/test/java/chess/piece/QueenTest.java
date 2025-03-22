package chess.piece;

import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @DisplayName("퀸은 위로 움직일 수 있다.")
    @Test
    void test1() {
        Queen queen = new Queen();
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래로 움직일 수 있다.")
    @Test
    void test2() {
        Queen queen = new Queen();
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 왼쪽으로 움직일 수 있다.")
    @Test
    void test3() {
        Queen queen = new Queen();
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 오른쪽으로 움직일 수 있다.")
    @Test
    void test4() {
        Queen queen = new Queen();
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 위+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test5() {
        Queen queen = new Queen();
        Position start = new Position(Column.A, Row.ONE);
        Position end = new Position(Column.H, Row.EIGHT);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 위+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test6() {
        Queen queen = new Queen();
        Position start = new Position(Column.H, Row.ONE);
        Position end = new Position(Column.A, Row.EIGHT);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래+오른쪽 대각선으로 움직일 수 있다.")
    @Test
    void test7() {
        Queen queen = new Queen();
        Position start = new Position(Column.A, Row.EIGHT);
        Position end = new Position(Column.H, Row.ONE);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isTrue();
    }

    @DisplayName("퀸은 아래+왼쪽 대각선으로 움직일 수 있다.")
    @Test
    void test8() {
        Queen queen = new Queen();
        Position start = new Position(Column.H, Row.EIGHT);
        Position end = new Position(Column.A, Row.ONE);

        Assertions.assertThat(queen.moveToDestination(start, end))
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
        Queen queen = new Queen();
        Position start = new Position(startColumn, startRow);
        Position end = new Position(endColumn, endRow);

        Assertions.assertThat(queen.moveToDestination(start, end))
                .isFalse();
    }
}