package chess;

import chess.piece.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @Test
    void name() {
        List<Piece> pieces = PieceFactory.createPieces();
        List<Piece> expected = Arrays.asList(
                new Bishop(0, 2, true),
                new Bishop(0, 5, true),
                new Bishop(7, 2, false),
                new Bishop(7, 5, false),
                new King(0, 4, true),
                new King(7, 4, false),
                new Knight(0, 1, true),
                new Knight(0, 6, true),
                new Knight(7, 1, false),
                new Knight(7, 6, false),
                new Queen(0, 3, true),
                new Queen(7, 3, false),
                new Pawn(1, 0, true),
                new Pawn(1, 1, true),
                new Pawn(1, 2, true),
                new Pawn(1, 3, true),
                new Pawn(1, 4, true),
                new Pawn(1, 5, true),
                new Pawn(1, 6, true),
                new Pawn(1, 7, true),
                new Pawn(6, 0, false),
                new Pawn(6, 1, false),
                new Pawn(6, 2, false),
                new Pawn(6, 3, false),
                new Pawn(6, 4, false),
                new Pawn(6, 5, false),
                new Pawn(6, 6, false),
                new Pawn(6, 7, false),
                new Rook(0, 0, true),
                new Rook(0, 7, true),
                new Rook(7, 7, false),
                new Rook(7, 0, false)
        );
        assertThat(pieces).containsAll(expected);
    }
}