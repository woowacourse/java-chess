package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Movement;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Square crossSource = Square.of(File.D, Rank.ONE);
        Square crossTarget = Square.of(File.D, Rank.THREE);

        Square diagonalSource = Square.of(File.D, Rank.ONE);
        Square diagonalTarget = Square.of(File.B, Rank.THREE);

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
        Square source = Square.of(File.D, Rank.ONE);
        Square target = Square.of(File.F, Rank.FOUR);
        Movement movement = new Movement(source, target);

        //when
        boolean canMove = queen.canMove(movement, null);

        //then
        assertThat(canMove).isFalse();
    }
}
