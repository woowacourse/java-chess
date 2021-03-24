package chess.domain.piece;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @Test
    void createPieces() {
        List<Piece> pieces = PieceFactory.createPieces();
        assertThat(pieces).containsAll(createPiecesForTest());
    }

    private List<Piece> createPiecesForTest() {
        return Arrays.asList(
                Piece.createRook(Color.BLACK, 0, 0),
                Piece.createKnight(Color.BLACK, 0, 1),
                Piece.createBishop(Color.BLACK, 0, 2),
                Piece.createQueen(Color.BLACK, 0, 3),
                Piece.createKing(Color.BLACK, 0, 4),
                Piece.createBishop(Color.BLACK, 0, 5),
                Piece.createKnight(Color.BLACK, 0, 6),
                Piece.createRook(Color.BLACK, 0, 7),
                Piece.createPawn(Color.BLACK, 1, 0),
                Piece.createPawn(Color.BLACK, 1, 1),
                Piece.createPawn(Color.BLACK, 1, 2),
                Piece.createPawn(Color.BLACK, 1, 3),
                Piece.createPawn(Color.BLACK, 1, 4),
                Piece.createPawn(Color.BLACK, 1, 5),
                Piece.createPawn(Color.BLACK, 1, 6),
                Piece.createPawn(Color.BLACK, 1, 7),

                Piece.createRook(Color.WHITE, 7, 0),
                Piece.createKnight(Color.WHITE, 7, 1),
                Piece.createBishop(Color.WHITE, 7, 2),
                Piece.createQueen(Color.WHITE, 7, 3),
                Piece.createKing(Color.WHITE, 7, 4),
                Piece.createBishop(Color.WHITE, 7, 5),
                Piece.createKnight(Color.WHITE, 7, 6),
                Piece.createRook(Color.WHITE, 7, 7),
                Piece.createPawn(Color.WHITE, 6, 0),
                Piece.createPawn(Color.WHITE, 6, 1),
                Piece.createPawn(Color.WHITE, 6, 2),
                Piece.createPawn(Color.WHITE, 6, 3),
                Piece.createPawn(Color.WHITE, 6, 4),
                Piece.createPawn(Color.WHITE, 6, 5),
                Piece.createPawn(Color.WHITE, 6, 6),
                Piece.createPawn(Color.WHITE, 6, 7)
        );
    }

}
