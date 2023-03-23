package chess.domain.board;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {

    @Test
    @DisplayName("피스가 바뀐다")
    void changePiece() {
        final var sourceSquare = new Square(new King(Color.WHITE));
        final var targetSquare = new Square(new BlackPawn());

        sourceSquare.changePiece(targetSquare);

        assertThat(sourceSquare.getPiece()).isExactlyInstanceOf(BlackPawn.class);
    }

    @DisplayName("Square를 EmptyPiece로 만든다")
    @Test
    void makeEmpty() {
        final var sourceSquare = new Square(new King(Color.WHITE));

        sourceSquare.makeEmpty();

        assertThat(sourceSquare.getPiece()).isExactlyInstanceOf(Empty.class);
    }

    @DisplayName("킹을 가지고 있다.")
    @Test
    void pieceKind_king_true() {
        final var square = new Square(new King(Color.WHITE));
        
        assertThat(square.pieceKind()).isEqualTo(Kind.KING);
    }
}
