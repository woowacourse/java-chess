package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2", "d4", "e5", "f6", "g7", "h8", "b4", "a5", "d2", "e1"})
    @DisplayName("bishop이 올바른 위치로 움직인다.")
    void move_success(final String input) {
        // given
        Bishop bishop = new Bishop(new Position("c3"));

        // when & then
        assertDoesNotThrow(() -> bishop.move(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a7", "b3", "c5", "d7"})
    @DisplayName("bishop이 올바른 위치로 움직이지 못하면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String input) {
        // given
        Bishop bishop = new Bishop(new Position("c3"));

        // when & then
        assertThatThrownBy(
                () -> bishop.move(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
