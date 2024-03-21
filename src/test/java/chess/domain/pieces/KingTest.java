package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("킹")
class KingTest {

    @DisplayName("움직일 수 있다")
    @Test
    void canMove() {
        //given
        Square source = Square.from("e1");
        Square destination = Square.from("e2");

        Piece king = new King(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = king.canMove(movement, null);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("움직일 수 없다.")
    @Test
    void canNotMove() {
        //given
        Square source = Square.from("e1");
        Square destination = Square.from("e3");

        Piece king = new King(Color.WHITE);
        Movement movement = new Movement(source, destination);

        //when
        boolean canMove = king.canMove(movement, null);

        //then
        assertThat(canMove).isFalse();
    }
}
