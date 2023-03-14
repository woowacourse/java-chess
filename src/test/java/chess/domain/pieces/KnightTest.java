package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
    @DisplayName("Knight가 정상적인 위치로 움직인다.")
    void move_success(final String movePosition) {
        // given
        Knight knight = new Knight(new Position("c3"));

        // when  & then
        Assertions.assertDoesNotThrow(
                () -> knight.move(movePosition)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a3", "b2", "b4", "d2", "d6", "e3", "e5"})
    @DisplayName("Knight가 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_knight_moves_invalid(final String movePosition) {
        // given
        Knight knight = new Knight(new Position("c3"));

        // when  & then
        assertThatThrownBy(
                () -> knight.move(movePosition)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
