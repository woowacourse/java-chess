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
        Pawn pawn = new Pawn(Color.BLACK, new Square(File.A, Rank.SEVEN));
        assertThat(pawn).isInstanceOf(Pawn.class);
    }


//    @Test
//    void firstSquareMovable() {
//        Pawn pawn = new Pawn(Color.BLACK, new Square(File.A, Rank.SEVEN));
//        Empty oneMoveSquare = new Empty(new Square(File.A, Rank.SIX));
//        Empty twoMoveSquare = new Empty(new Square(File.A, Rank.FIVE));
//        assertAll(
//                () -> assertThat(pawn.movable(oneMoveSquare)).isTrue(),
//                () -> assertThat(pawn.movable(twoMoveSquare)).isTrue());
//    }

    @Test
    void firstSquareCannotMovable() {
        Pawn pawn = new Pawn(Color.BLACK, new Square(File.A, Rank.SEVEN));
        Empty empty = new Empty(new Square(File.A, Rank.FOUR));
        assertThat(pawn.movable(empty)).isFalse();
    }
}
