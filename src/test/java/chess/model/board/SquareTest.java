package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("체스판 칸이 정상적으로 생성되는지 확인한다.")
    void constructor_givenPositionAndPiece_thenSuccess() {
        final Position position = new Position(Rank.FIRST, File.A);
        final Piece piece = new Piece(Color.WHITE, Type.PAWN);

        final Square square = assertDoesNotThrow(() -> new Square(position, piece));

        assertThat(square).isExactlyInstanceOf(Square.class);
    }
}
