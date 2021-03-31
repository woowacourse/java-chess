package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SquareTest {

    @Test
    void create(){
        Square square = new Square(new Position(Vertical.A, Horizontal.FIVE));
        assertThat(square).isInstanceOf(Square.class);
    }
}