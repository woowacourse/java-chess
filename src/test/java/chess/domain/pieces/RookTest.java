package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"c1", "c2", "c4", "c5", "c6", "c7", "c8",
            "a3", "b3", "d3", "e3", "f3", "g3", "h3"})
    @DisplayName("Rook이 올바른 위치로 이동한다.")
    void move_success(final String givenPosition) {
        // given
        Rook rook = new Rook(new Position("c3"));

        // when & then
        assertDoesNotThrow(
                () -> rook.move(givenPosition)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "b2", "f5", "f7"})
    @DisplayName("Rook이 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String givenPosition) {
        // given
        Rook rook = new Rook(new Position("c3"));

        // when & then
        assertThatThrownBy(
                () -> rook.move(givenPosition)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
