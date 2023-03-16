package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Path;
import chess.Position;
import java.util.Optional;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    void test_searchPathTo() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = queen.searchPathTo(initialPosition, new Position(5, 8), Optional.empty());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 2), new Position(5, 3),
                        new Position(5, 4), new Position(5, 5),
                        new Position(5, 6), new Position(5, 7));
    }

    @Test
    void test_searchPathTo2() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = queen.searchPathTo(initialPosition, new Position(8, 4), Optional.empty());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(new Position(6, 2), new Position(7, 3));
    }

    @Test
    void test_searchPathTo3() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 5);
        Path path = queen.searchPathTo(initialPosition, new Position(5, 1), Optional.empty());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 4),
                        new Position(5, 3),
                        new Position(5, 2));
    }
}
