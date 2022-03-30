package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"1:1", "1:-1", "-1:1", "-1:-1"}, delimiter = ':')
    @DisplayName("queen 기물 대각선 이동 위치 검증 - true")
    void checkQueenPositionDiagonal(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovableDot(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0:1", "0:-1", "1:0", "-1:-0"}, delimiter = ':')
    @DisplayName("queen 기물 상하좌우 이동 위치 검증 - true")
    void checkQueenPositionUpDownLeftRight(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovableDot(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "1:2", "2:-1", "-1:-2"}, delimiter = ':')
    @DisplayName("queen 기물 이동 위치 검증 - false")
    void checkBishopPositionWhenFalse(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovableDot(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }
}
