package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b2", "c2", "d2", "b3", "d3", "b4", "c4", "d4"})
    @DisplayName("king이 올바른 위치로 움직인다.")
    void move_success(final String input) {
        // given
        King king = new King(new Position("c3"));

        // when & then
        assertDoesNotThrow(() -> king.move(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "c1", "d1", "b5"})
    @DisplayName("King이 올바른 위치로 움직이지 못하면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String input) {
        // given
        King king = new King(new Position("c3"));

        // when & then
        assertThatThrownBy(
                () -> king.move(input)
        ).isInstanceOf(IllegalArgumentException.class);
     }
}
