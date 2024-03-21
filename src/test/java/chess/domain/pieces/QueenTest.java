package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("퀸")
class QueenTest {

    @DisplayName("움직일 수 있다")
    @Test
    void canMove() {
        //given
        Square crossSource = Square.from("d1");
        Square crossDestination = Square.from("d3");

        Square diagonalSource = Square.from("d1");
        Square diagonalDestination = Square.from("b3");

        Piece queen = new Queen(Color.WHITE);
        Movement crossMovement = new Movement(crossSource, crossDestination);
        Movement diagonalMovement = new Movement(diagonalSource, diagonalDestination);

        //when
        boolean canCrossMove = queen.canMove(crossMovement, null);
        boolean canDiagonalMove = queen.canMove(diagonalMovement, null);

        //then
        assertThat(canCrossMove).isTrue();
        assertThat(canDiagonalMove).isTrue();
    }
}
