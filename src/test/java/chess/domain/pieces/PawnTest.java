package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4"})
    @DisplayName("폰이 올바른 위치로 움직인다.")
    void move_success(final String givenPosition) {
        // given
        Pawn pawn = new Pawn(new Position("c2"));

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.move(givenPosition));
     }

     @ParameterizedTest
     @ValueSource(strings = {"c1", "a1", "b7", "d2"})
     @DisplayName("폰이 올바르지 않은 위치로 움직이면 에러를 발생한다.")
     void throws_exception_when_pawn_moves_invalid(final String givenPosition) {
         // given
         Pawn pawn = new Pawn(new Position("c2"));

         // when & then
         assertThatThrownBy(
                 () -> pawn.move(givenPosition)
         ).isInstanceOf(IllegalArgumentException.class);
      }

}
