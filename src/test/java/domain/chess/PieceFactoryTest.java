package domain.chess;

import domain.chess.piece.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {
   @Test
    void name() {
        List<Piece> pieces = PieceFactory.createPieces();
        List<Piece> expected = Arrays.asList(
                new Bishop("B",0, 2, true),
                new Bishop("B", 0, 5, true),
                new Bishop("b", 7, 2, false),
                new Bishop("b", 7, 5, false),
                new King("k",0, 4, true),
                new King("k",7, 4, false),
                new Knight("N", 0, 1, true),
                new Knight("N",0, 6, true),
                new Knight("n",7, 1, false),
                new Knight("n",7, 6, false),
                new Queen("Q",0, 3, true),
                new Queen("q",7, 3, false),
                new Pawn("P",1, 0, true),
                new Pawn("P",1, 1, true),
                new Pawn("P",1, 2, true),
                new Pawn("P",1, 3, true),
                new Pawn("P",1, 4, true),
                new Pawn("P",1, 5, true),
                new Pawn("P",1, 6, true),
                new Pawn("P",1, 7, true),
                new Pawn("p",6, 0, false),
                new Pawn("p",6, 1, false),
                new Pawn("p",6, 2, false),
                new Pawn("p",6, 3, false),
                new Pawn("p",6, 4, false),
                new Pawn("p",6, 5, false),
                new Pawn("p",6, 6, false),
                new Pawn("p",6, 7, false),
                new Rook("R", 0, 0, true),
                new Rook("R",0, 7, true),
                new Rook("r",7, 7, false),
                new Rook("r", 7, 0, false)
        );
        assertThat(pieces).containsAll(expected);
    }
}