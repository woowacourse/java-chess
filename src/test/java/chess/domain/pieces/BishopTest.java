package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.exception.PieceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"c3:a1", "c3:b2", "c3:d4", "c3:e5", "c3:f6", "c3:g7", "c3:a5", "c3:d2",
            "c3:e1"}, delimiter = ':')
    @DisplayName("bishop이 올바른 위치로 움직인다.")
    void move_success(final String start, final String end) {
        // given
        Bishop bishop = new Bishop(new Name("b"));

        // when & then
        assertDoesNotThrow(() -> bishop.canMove(Position.from(start), Position.from(end)));
    }

    @ParameterizedTest
    @CsvSource(value = {"c3:a7", "c3:b3", "c3:c5", "c3:d7"}, delimiter = ':')
    @DisplayName("bishop이 올바른 위치로 움직이지 못하면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        Bishop bishop = new Bishop(new Name("b"));

        // when & then
        assertThatThrownBy(() -> bishop.canMove(Position.from(start), Position.from(end)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.BISHOP_INVALID_MOVE.getMessage());
    }
}
