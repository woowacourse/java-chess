package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Path;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    void test_searchPathTo() {
        Piece Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = Rook.searchPathTo(initialPosition, new Position(5, 8), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 2), new Position(5, 3),
                        new Position(5, 4), new Position(5, 5),
                        new Position(5, 6), new Position(5, 7));
    }

    @Test
    void test_searchPathTo2() {
        Rook Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 5);
        Path path = Rook.searchPathTo(initialPosition, new Position(5, 1), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 4),
                        new Position(5, 3),
                        new Position(5, 2));
    }

    @Test
    void test_searchPathTo3() {
        Rook Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 5);

        assertThatThrownBy(
                () -> Rook.searchPathTo(initialPosition,
                        new Position(5, 1),
                        new King(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
