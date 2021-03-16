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

        List<Piece> oncePieces = Arrays.asList(
                new Piece(Color.BLACK, Shape.QUEEN),
                new Piece(Color.WHITE, Shape.QUEEN),
                new Piece(Color.BLACK, Shape.KING),
                new Piece(Color.WHITE, Shape.KING)
        );

        List<Piece> twicePieces = Arrays.asList(
                new Piece(Color.BLACK, Shape.ROOK),
                new Piece(Color.WHITE, Shape.ROOK),
                new Piece(Color.BLACK, Shape.NIGHT),
                new Piece(Color.WHITE, Shape.NIGHT),
                new Piece(Color.BLACK, Shape.BISHOP),
                new Piece(Color.WHITE, Shape.BISHOP)
        );

        List<Piece> octoPieces = Arrays.asList(
                new Piece(Color.BLACK, Shape.PAWN),
                new Piece(Color.WHITE, Shape.PAWN)
        );

        List<Piece> result = new ArrayList<>(oncePieces);

        for(int i=0; i<2; i++) {
            result.addAll(twicePieces);
        }
        for(int i=0; i<8; i++) {
            result.addAll(octoPieces);
        }

        return result;
    }

}