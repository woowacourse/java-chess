package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.exception.PieceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"c3,c1", "c3,c2", "c3,c4", "c3,c5", "c3,c6", "c3,c7", "c3,c8",
            "c3,a3", "c3,b3", "c3,d3", "c3,e3", "c3,f3", "c3,g3", "c3,h3"})
    @DisplayName("Rook이 올바른 위치로 이동한다.")
    void move_success(final String start, final String end) {
        // given
        Rook rook = new Rook(new Name("r"));

        // when & then
        assertDoesNotThrow(
                () -> rook.canMove(Position.from(start), Position.from(end))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,b1", "c3,b2", "c3,f5", "c3,f7"})
    @DisplayName("Rook이 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String start, final String end) {
        // given
        Rook rook = new Rook(new Name("r"));

        // when & then
        assertThatThrownBy(
                () -> rook.canMove(Position.from(start), Position.from(end)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.ROOK_INVALID_MOVE.getMessage());
    }
}
