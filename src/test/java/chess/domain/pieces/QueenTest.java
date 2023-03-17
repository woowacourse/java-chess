package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"c3,c1", "c3,c2", "c3,c4", "c3,c5", "c3,c6", "c3,c7",
            "c3,c8", "c3,a3", "c3,b3", "c3,d3", "c3,e3", "c3,f3", "c3,g3", "c3,h3"})
    @DisplayName("Queen이 올바른 위치로 이동한다. (사방)")
    void move_success_like_rook(final String start, final String end) {
        // given
        Queen queen = new Queen(new Name("q"));

        // when & then
        assertDoesNotThrow(
                () -> queen.canMove(Position.from(start), Position.from(end))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,a1", "c3,b2", "c3,d4", "c3,e5", "c3,f6", "c3,g7",
            "c3,h8", "c3,b4", "c3,a5", "c3,d2", "c3,e1"})
    @DisplayName("Queen이 올바른 위치로 움직인다. (대각선)")
    void move_success_like_bishop(final String start, final String end) {
        // given
        Queen queen = new Queen(new Name("q"));

        // when & then
        assertDoesNotThrow(() -> queen.canMove(Position.from(start), Position.from(end)));
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,b1", "c3,f5", "c3,f7"})
    @DisplayName("Queen이 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String start, final String end) {
        // given
        Queen queen = new Queen(new Name("q"));

        // when & then
        assertThatThrownBy(
                () -> queen.canMove(Position.from(start), Position.from(end))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
