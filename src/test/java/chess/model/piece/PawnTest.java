package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.Pawn;
import chess.piece.Piece;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    void createPawn() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }


    @Test
    void firstSquareMovable() {
        Pawn pawn = new Pawn(Color.WHITE);
        Empty oneMoveSquare = new Empty();
        Empty twoMoveSquare = new Empty();
        assertAll(
                () -> assertThat(pawn.movable(oneMoveSquare)).isTrue(),
                () -> assertThat(pawn.movable(twoMoveSquare)).isTrue());
    }

    @Test
    void firstSquareCannotMovable() {
        Pawn pawn = new Pawn(Color.BLACK);
        Empty empty = new Empty();
        assertThat(pawn.movable(empty)).isFalse();
    }

    @Test
    void moveToDiagonalInLeft() {
        Pawn pawn = new Pawn(Color.BLACK);
        Piece diagonalPiece = new Pawn(Color.WHITE);
        assertThat(pawn.movable(diagonalPiece)).isTrue();
    }

    @Test
    void moveToDiagonalInRight() {
        Pawn pawn = new Pawn(Color.BLACK);
        Piece diagonalPiece = new Pawn(Color.WHITE);
        assertThat(pawn.movable(diagonalPiece)).isTrue();
    }

    @Test
    void cannotMoveToDiagonal() {
        Pawn pawn = new Pawn(Color.BLACK);
        Piece diagonalPiece = new Empty();

        assertThat(pawn.movable(diagonalPiece)).isFalse();
    }

    @Test
    void cannotMoveTwoSquare() {
        Pawn pawn = new Pawn(Color.BLACK);
        Piece target = new Empty();
        assertThat(pawn.movable(target)).isFalse();
    }

    @Test
    void BlackPawnCannotMoveInLastColumn() {
        Pawn pawn = new Pawn(Color.BLACK);
        Empty empty = new Empty();
        assertThat(pawn.movable(empty)).isFalse();
    }

    @Test
    void WhitePawnCannotMoveInLastColumn() {
        Pawn pawn = new Pawn(Color.WHITE);
        Empty empty = new Empty();
        assertThat(pawn.movable(empty)).isFalse();
    }
}
