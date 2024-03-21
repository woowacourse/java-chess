package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("나이트")
class KnightTest {

    @DisplayName("움직일 수 있다")
    @Test
    void canMove() {
        //given
        Square source = Square.from("b1");
        Square destination = Square.from("c3");

        Piece knight = new Knight(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = knight.canMove(movement, null);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("움직일 수 없다.")
    @Test
    void canNotMove() {
        //given
        Square source = Square.from("b1");
        Square destination = Square.from("b3");

        Piece knight = new Knight(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = knight.canMove(movement, null);

        //then
        assertThat(canMove).isFalse();
    }
}
