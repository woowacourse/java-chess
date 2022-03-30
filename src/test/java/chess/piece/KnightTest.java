package chess.piece;

import chess.chessgame.MovingPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"e7", "f6", "f4", "e3", "c3", "b4", "b6", "c7"})
    @DisplayName("knight 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(String input) {
        Knight knight = new Knight(Color.BLACK);
        assertThat(knight.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "d7", "g5", "h3", "g2", "a5"})
    @DisplayName("knight 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(String input) {
        Knight knight = new Knight(Color.BLACK);
        assertThat(knight.isMovable(new MovingPosition("d5", input))).isFalse();
    }
}
