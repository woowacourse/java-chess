package chess.model.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.position.Path;
import chess.model.domain.position.Position;
import chess.model.exception.CantMoveFromToException;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    void test_searchPathTo() {

        Piece piece = new Knight(Color.WHITE);

        Position initialPosition = new Position(2, 1);
        Path path = piece.searchPathTo(initialPosition, new Position(3, 3), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @Test
    void test_searchPathTo2() {

        Piece piece = new Knight(Color.WHITE);

        Position initialPosition = new Position(2, 1);

        assertThatThrownBy(() ->
                piece.searchPathTo(initialPosition,
                        new Position(4, 5),
                        Empty.getInstance()))
                .isInstanceOf(CantMoveFromToException.class);
    }
}
