package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {

    @DisplayName("피스가 바뀐다")
    @Test
    void changePiece() {
        final var sourceSquare = new Square(new King(Color.WHITE));
        final var targetSquare = new Square(new Pawn(Color.BLACK));

        sourceSquare.changePiece(targetSquare);

        assertThat(sourceSquare.getPiece()).isExactlyInstanceOf(Pawn.class);
    }

    @DisplayName("Square를 EmptyPiece로 만든다")
    @Test
    void makeEmpty() {
        final var sourceSquare = new Square(new King(Color.WHITE));

        sourceSquare.makeEmpty();

        assertThat(sourceSquare.getPiece()).isExactlyInstanceOf(Empty.class);
    }
}
