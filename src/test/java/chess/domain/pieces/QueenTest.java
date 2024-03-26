package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("퀸")
class QueenTest {

    Piece queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(Color.WHITE);
    }

    @DisplayName("움직일 수 있다")
    @Test
    void canMove() {
        //given
        Square crossSource = Square.from("d1");
        Square crossTarget = Square.from("d3");

        Square diagonalSource = Square.from("d1");
        Square diagonalTarget = Square.from("b3");

        Movement crossMovement = new Movement(crossSource, crossTarget);
        Movement diagonalMovement = new Movement(diagonalSource, diagonalTarget);

        //when
        boolean canCrossMove = queen.canMove(crossMovement, null);
        boolean canDiagonalMove = queen.canMove(diagonalMovement, null);

        //then
        assertThat(canCrossMove).isTrue();
        assertThat(canDiagonalMove).isTrue();
    }

    @DisplayName("움직일 수 없다.")
    @Test
    void canNotMove() {
        //given
        Square source = Square.from("d1");
        Square target = Square.from("f4");
        Movement movement = new Movement(source, target);

        //when
        boolean canMove = queen.canMove(movement, null);

        //then
        assertThat(canMove).isFalse();
    }
}
