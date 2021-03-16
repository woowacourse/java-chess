package chess.domain.piece;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
                new Piece(Color.BLACK, Shape.QUEEN, new Position(0,3)),
                new Piece(Color.WHITE, Shape.QUEEN, new Position(7,3)),
                new Piece(Color.BLACK, Shape.KING, new Position(0,4)),
                new Piece(Color.WHITE, Shape.KING, new Position(7,4)),
                new Piece(Color.BLACK, Shape.ROOK, new Position(0,0)),
                new Piece(Color.WHITE, Shape.ROOK, new Position(7,0)),
                new Piece(Color.BLACK, Shape.NIGHT, new Position(0,1)),
                new Piece(Color.WHITE, Shape.NIGHT, new Position(7,1)),
                new Piece(Color.BLACK, Shape.BISHOP, new Position(0,2)),
                new Piece(Color.WHITE, Shape.BISHOP, new Position(7,2)),
                new Piece(Color.BLACK, Shape.ROOK, new Position(0,7)),
                new Piece(Color.WHITE, Shape.ROOK, new Position(7,7)),
                new Piece(Color.BLACK, Shape.NIGHT, new Position(0,6)),
                new Piece(Color.WHITE, Shape.NIGHT, new Position(7,6)),
                new Piece(Color.BLACK, Shape.BISHOP, new Position(0,5)),
                new Piece(Color.WHITE, Shape.BISHOP, new Position(7,5)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,0)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,0)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,1)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,1)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,2)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,2)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,3)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,3)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,4)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,4)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,5)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,5)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,6)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,6)),
                new Piece(Color.BLACK, Shape.PAWN, new Position(1,7)),
                new Piece(Color.WHITE, Shape.PAWN, new Position(6,7))
        );
    }

}