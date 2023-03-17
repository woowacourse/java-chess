package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.exception.PieceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest(name = "King move success : {0} to {1}")
    @CsvSource(value = {"c3:b2", "c3:c2", "c3:d2", "c3:b3", "c3:d3", "c3:b4", "c3:c4", "c3:d4"}, delimiter = ':')
    @DisplayName("king이 올바른 위치로 움직인다.")
    void move_success(final String start, final String end) {
        // given
        King king = new King(new Name("k"));

        // when & then
        assertDoesNotThrow(() -> king.canMove(Position.from(start), Position.from(end)));
    }

    @ParameterizedTest(name = "King move fail : {0} to {1}")
    @CsvSource(value = {"c3:b1", "c3:c1", "c3:d1", "c3:b5"}, delimiter = ':')
    @DisplayName("King이 올바른 위치로 움직이지 못하면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        King king = new King(new Name("k"));

        // when & then
        assertThatThrownBy(() -> king.canMove(Position.from(start), Position.from(end)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.KING_INVALID_MOVE.getMessage());
    }
}
