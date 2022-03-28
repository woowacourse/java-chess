package chess.piece;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"-1:-1", "-1:1"}, delimiter = ':')
    @DisplayName("white pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenWhiteTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:-1", "1:1", "2:3"}, delimiter = ':')
    @DisplayName("white pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenWhiteFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:-1", "1:1"}, delimiter = ':')
    @DisplayName("black pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenBlackTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:-1", "-1:1", "2:3"}, delimiter = ':')
    @DisplayName("black pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenBlackFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:0", "-2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> true")
    void checkPositionWhenWhiteFirstTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new Position(6, 6), new Position(6 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-3:0", "3:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> false")
    void checkPositionWhenWhiteFirstTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new Position(6, 6), new Position(6 + a, 6 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> true")
    void checkPositionWhenBlackFirstTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new Position(1, 1), new Position(1 + a, 1 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-3:0", "3:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> false")
    void checkPositionWhenBlackFirstTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new Position(1, 1), new Position(1 + a, 1 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> true")
    void checkPositionWhenWhiteTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new Position(7, 6), new Position(7 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-2:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> false")
    void checkPositionWhenWhiteTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new Position(7, 6), new Position(7 + a, 6 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> true")
    void checkPositionWhenBlackTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new Position(2, 6), new Position(2 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-2:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> false")
    void checkPositionWhenBlackTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new Position(2, 6), new Position(2 + a, 6 + b))).isFalse();
    }

    @Test
    @DisplayName("source와 target 사이에 폰이 이동가능한 위치 리스트 반환- white ")
    void checkAllPositionOfPossibleWhite() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.computeBetweenTwoPosition(new Position(6, 0), new Position(4, 0)))
                .isEqualTo(List.of(new Position(5, 0)));
    }

    @Test
    @DisplayName("source와 target 사이에 폰이 이동가능한 위치 리스트 반환 - black")
    void checkAllPositionOfPossibleBlack() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.computeBetweenTwoPosition(new Position(1, 0), new Position(3, 0)))
                .isEqualTo(List.of(new Position(2, 0)));
    }
}
