package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"c3,c4", "c3,c5"})
    @DisplayName("폰이 올바른 위치로 움직인다.")
    void move_success_lower_case(final String start, final String end) {
        // given
        Pawn pawn = new Pawn(new Name("p"));

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(Position.from(start), Position.from(end)));
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,c2", "c3,a2", "c3,b7", "c3,d8"})
    @DisplayName("폰이 올바르지 않은 위치로 움직이면 에러를 발생한다.")
    void throws_exception_when_lower_pawn_moves_invalid(final String start, final String end) {
        // given
        Pawn pawn = new Pawn(new Name("p"));

        // when & then
        assertThatThrownBy(
                () -> pawn.canMove(Position.from(start), Position.from(end))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"c7,c6", "c7,c5"})
    @DisplayName("폰이 올바른 위치로 움직인다.")
    void move_success_upper_case(final String start, final String end) {
        // given
        Pawn pawn = new Pawn(new Name("P"));

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(Position.from(start), Position.from(end)));
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,c4", "c3,a2", "c3,b7", "c3,d8"})
    @DisplayName("폰이 올바르지 않은 위치로 움직이면 에러를 발생한다.")
    void throws_exception_when_upper_pawn_moves_invalid(final String start, final String end) {
        // given
        Pawn pawn = new Pawn(new Name("P"));

        // when & then
        assertThatThrownBy(
                () -> pawn.canMove(Position.from(start), Position.from(end))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 처음에만 두 번 움직일 수 있다.")
    void throws_exception_when_pawn_move_double_after_first() {
        // given
        Pawn pawn = new Pawn(new Name("p"));

        // when
        pawn.canMove(Position.from("a2"), Position.from("a3"));

        // then
        assertThatThrownBy(() -> pawn.canMove(Position.from("a3"), Position.from("a5")))
                .isInstanceOf(IllegalArgumentException.class);
     }
}
