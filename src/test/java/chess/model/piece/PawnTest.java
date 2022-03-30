package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    void createPawn() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.SEVEN));
        assertThat(pawn).isInstanceOf(Pawn.class);
    }


    @Test
    void firstSquareMovable() {
        Pawn pawn = new Pawn(Color.WHITE, Square.of(File.A, Rank.TWO));
        Empty oneMoveSquare = new Empty(Square.of(File.A, Rank.THREE));
        Empty twoMoveSquare = new Empty(Square.of(File.A, Rank.FOUR));
        assertAll(
                () -> assertThat(pawn.movable(oneMoveSquare)).isTrue(),
                () -> assertThat(pawn.movable(twoMoveSquare)).isTrue());
    }

    @Test
    void firstSquareCannotMovable() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.SEVEN));
        Empty empty = new Empty(Square.of(File.A, Rank.FOUR));
        assertThat(pawn.movable(empty)).isFalse();
    }

    @Test
    void moveToDiagonalInLeft() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.SEVEN));
        Piece diagonalPiece = new Pawn(Color.WHITE, Square.of(File.B, Rank.SIX));
        assertThat(pawn.movable(diagonalPiece)).isTrue();
    }

    @Test
    void moveToDiagonalInRight() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.H, Rank.SEVEN));
        Piece diagonalPiece = new Pawn(Color.WHITE, Square.of(File.G, Rank.SIX));
        assertThat(pawn.movable(diagonalPiece)).isTrue();
    }

    @Test
    void cannotMoveToDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.SEVEN));
        Piece diagonalPiece = new Empty(Square.of(File.B, Rank.SIX));

        assertThat(pawn.movable(diagonalPiece)).isFalse();
    }

    @Test
    void cannotMoveTwoSquare() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.FIVE));
        Piece target = new Empty(Square.of(File.A, Rank.THREE));
        assertThat(pawn.movable(target)).isFalse();
    }

    @Test
    void BlackPawnCannotMoveInLastColumn() {
        Pawn pawn = new Pawn(Color.BLACK, Square.of(File.A, Rank.ONE));
        Empty empty = new Empty(Square.of(File.A, Rank.FOUR));
        assertThat(pawn.movable(empty)).isFalse();
    }

    @Test
    void WhitePawnCannotMoveInLastColumn() {
        Pawn pawn = new Pawn(Color.WHITE, Square.of(File.A, Rank.EIGHT));
        Empty empty = new Empty(Square.of(File.A, Rank.FOUR));
        assertThat(pawn.movable(empty)).isFalse();
    }
}
