package chess.piece;

import chess.chessgame.MovingPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"c6", "d6", "e6", "c5", "e5", "c4", "d4", "e4"})
    @DisplayName("king 기물 이동 위치 검증")
    void checkKingPosition(String input) {
        King king = new King(Color.BLACK);
        assertThat(king.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b6", "d7", "b3", "d1", "a4", "h5", "g3"})
    @DisplayName("king 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(String input) {
        King king = new King(Color.BLACK);
        assertThat(king.isMovable(new MovingPosition("d5", input))).isFalse();
    }
}