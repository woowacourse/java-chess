package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Optional;
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
                        Optional.of(new King(Color.BLACK))))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void test_searchPathTo2() {

        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = piece.searchPathTo(initialPosition, new Position(5, 2), Optional.empty());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @Test
    void test_searchPathTo3() {

        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = piece.searchPathTo(initialPosition, new Position(5, 2), Optional.of(new Queen(Color.WHITE)));

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }
}
