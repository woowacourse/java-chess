package chess.domain.board;

import chess.domain.board.piece.King;
import chess.domain.board.piece.Owner;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SquareTest {

    @Test
    void create(){
        Square square = new Square(new Position(Vertical.A, Horizontal.FIVE), new King(Owner.BLACK));
        assertThat(square).isInstanceOf(Square.class);
    }
}