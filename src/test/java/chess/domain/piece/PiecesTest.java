package chess.domain.piece;

import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PiecesTest {

    @Test
    void findPieceByPosition() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        assertThat(pieces.findPieceByPosition(Color.BLACK, new Position(0, 0)))
                .isEqualTo(Piece.createPawn(Color.BLACK, 0, 0));

        assertThatThrownBy(() -> pieces.findPieceByPosition(Color.WHITE, new Position(0, 0)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);

        assertThatThrownBy(() -> pieces.findPieceByPosition(Color.BLACK, new Position(0, 1)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }

    @Test
    void catchPiece() {
        Pieces pieces = new Pieces(Arrays.asList(
                Piece.createPawn(Color.WHITE, 0, 0),
                Piece.createPawn(Color.BLACK, 0, 0)
        ));

        pieces.catchPiece(Color.WHITE);
        assertThat(pieces.getPieces()).containsExactly(Piece.createPawn(Color.WHITE, 0, 0));
    }
}