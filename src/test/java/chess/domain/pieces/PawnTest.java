package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"c3,c4", "c3,c5"})
    @DisplayName("폰이 올바른 위치로 움직인다.")
    void move_success(final String start, final String end) {
        // given
        Pawn pawn = new Pawn(new Name("p"));

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(start, end));
     }

     @ParameterizedTest
     @CsvSource(value = {"c3,c2", "c3,a2", "c3,b7", "c3,d8"})
     @DisplayName("폰이 올바르지 않은 위치로 움직이면 에러를 발생한다.")
     void throws_exception_when_pawn_moves_invalid(final String start, final String end) {
         // given
         Pawn pawn = new Pawn(new Name("p"));

         // when & then
         assertThatThrownBy(
                 () -> pawn.canMove(start, end)
         ).isInstanceOf(IllegalArgumentException.class);
      }

}
