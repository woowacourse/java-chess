package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {

    @ParameterizedTest
    @ValueSource(strings = {"c1", "c2", "c4", "c5", "c6", "c7", "c8",
            "a3", "b3", "d3", "e3", "f3", "g3", "h3"})
    @DisplayName("Queen이 올바른 위치로 이동한다. (사방)")
    void move_success_like_rook(final String givenPosition) {
        // given
        Queen queen = new Queen(new Position("c3"));

        // when & then
        assertDoesNotThrow(
                () -> queen.move(givenPosition)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2", "d4", "e5", "f6", "g7", "h8", "b4", "a5", "d2", "e1"})
    @DisplayName("Queen이 올바른 위치로 움직인다. (대각선)")
    void move_success_like_bishop(final String input) {
        // given
        Queen queen = new Queen(new Position("c3"));

        // when & then
        assertDoesNotThrow(() -> queen.move(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "f5", "f7"})
    @DisplayName("Queen이 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String givenPosition) {
        // given
        Queen queen = new Queen(new Position("c3"));

        // when & then
        assertThatThrownBy(
                () -> queen.move(givenPosition)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
