package chess.piece;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"-1:-1", "-1:1"}, delimiter = ':')
    @DisplayName("white pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenWhiteTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(Pair.of(4, 4), Pair.of(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:-1", "1:1", "2:3"}, delimiter = ':')
    @DisplayName("white pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenWhiteFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(Pair.of(4, 4), Pair.of(4 + a, 4 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:-1", "1:1"}, delimiter = ':')
    @DisplayName("black pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenBlackTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(Pair.of(4, 4), Pair.of(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:-1", "-1:1", "2:3"}, delimiter = ':')
    @DisplayName("black pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenBlackFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(Pair.of(4, 4), Pair.of(4 + a, 4 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:0", "-2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> true")
    void checkPositionWhenWhiteFirstTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(Pair.of(6, 6), Pair.of(6 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-3:0", "3:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> false")
    void checkPositionWhenWhiteFirstTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(Pair.of(6, 6), Pair.of(6 + a, 6 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> true")
    void checkPositionWhenBlackFirstTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(Pair.of(1, 1), Pair.of(1 + a, 1 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-3:0", "3:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> false")
    void checkPositionWhenBlackFirstTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(Pair.of(1, 1), Pair.of(1 + a, 1 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> true")
    void checkPositionWhenWhiteTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(Pair.of(7, 6), Pair.of(7 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-2:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> false")
    void checkPositionWhenWhiteTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(Pair.of(7, 6), Pair.of(7 + a, 6 + b))).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> true")
    void checkPositionWhenBlackTurnTrue(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(Pair.of(2, 6), Pair.of(2 + a, 6 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"-2:0", "2:0"}, delimiter = ':')
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> false")
    void checkPositionWhenBlackTurnFalse(int a, int b) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(Pair.of(2, 6), Pair.of(2 + a, 6 + b))).isFalse();
    }
}
