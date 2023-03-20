package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Path;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class KingTest {


    @Test
    void test_searchPathTo() {

        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(6, 1);

        assertThatThrownBy(
                () -> piece.searchPathTo(initialPosition,
                        new Position(8, 1),
                        new King(Color.BLACK)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void test_searchPathTo2() {

        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = piece.searchPathTo(initialPosition, new Position(5, 2), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @Test
    void test_searchPathTo3() {
        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(5, 1);

        assertThatThrownBy(() -> piece.searchPathTo(initialPosition,
                new Position(5, 2), piece))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
