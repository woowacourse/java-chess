package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Path;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    void test_searchPathTo() {

        Piece bishop = new Bishop(Color.WHITE);

        Position initialPosition = new Position(1, 1);
        Path path = bishop.searchPathTo(initialPosition, new Position(5, 5), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(2, 2), new Position(3, 3),
                        new Position(4, 4));
    }

    @Test
    void test_searchPathTo2() {

        Bishop bishop = new Bishop(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = bishop.searchPathTo(initialPosition, new Position(8, 4), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(new Position(6, 2), new Position(7, 3));
    }

    @Test
    void test_searchPathTo4() {

        Bishop bishop = new Bishop(Color.WHITE);

        Position initialPosition = new Position(5, 5);

        assertThatThrownBy(
                () -> bishop.searchPathTo(initialPosition,
                        new Position(5, 1),
                        new King(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
