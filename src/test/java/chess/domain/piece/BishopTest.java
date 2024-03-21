package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("비숍")
class BishopTest {

    @DisplayName("움직일 수 있다")
    @Test
    void canMove() {
        //given
        Square source = Square.from("c1");
        Square destination = Square.from("d2");

        Piece bishop = new Bishop(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = bishop.canMove(movement, null);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("움직일 수 없다.")
    @Test
    void canNotMove() {
        //given
        Square source = Square.from("c1");
        Square destination = Square.from("c2");

        Piece bishop = new Bishop(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = bishop.canMove(movement, null);

        //then
        assertThat(canMove).isFalse();
    }
}
